package ai.fassto.connectivity.domain.purchaseorder.application.helper.update;

import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.notification.alimtalk.PurchaseOrderAlimTalk;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.repository.PurchaseOrderRepository;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.CenterArrivalInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.CenterArrivalType;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static ai.fassto.connectivity.dataaccess.common.valueobject.SlipNoDivType.IN;
import static ai.fassto.connectivity.dataaccess.common.valueobject.SlipNoDivType.IN_CHECK;

@Slf4j
@Component
public class PurchaseOrderUpdateRequestSalesHelper extends PurchaseOrderUpdateRequestHelper {
    private final OrderType ORDER_TYPE = OrderType.SALES;

    public PurchaseOrderUpdateRequestSalesHelper(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderAlimTalk purchaseOrderAlimTalk) {
        super(purchaseOrderRepository, purchaseOrderAlimTalk);
    }

    @Override
    public boolean is(OrderType orderType) {
        return ORDER_TYPE.equals(orderType);
    }

    @Transactional //센터도착
    @Override //
    public void persistWhenArrived(PurchaseOrder purchaseOrder) {
        InOrder inOrder = checkInOrder(purchaseOrder);
        checkItems(purchaseOrder.getOrderLineList());

        /* 센터도착 centerArrivalTimeUpdate */
        purchaseOrder.arrive(inOrder);
        purchaseOrderRepository.updateCenterArrivalInfo(purchaseOrder);
    }

    @Transactional
    @Override
    public void sendNotificationWhenArrived(PurchaseOrder purchaseOrder) {
        // 센터도착시 3가지 경우에 대해 각 알림톡 전송
        // 1.센터도착 알림톡 전송
        purchaseOrderAlimTalk.sendCenArrivalTalk(purchaseOrder);
        // 2.익일 출고 => 당일출고로 변환 권유 알림톡 전송
        if (isAbleToBeChangedToPlusDayZero(purchaseOrder.getCenterArrivalInfo())) {
            purchaseOrderAlimTalk.sendConvertAdviceTalk(purchaseOrder);
        }
        // 3.지연도착 하는 경우, 고객에게 알림톡 전송
//        if(CenterArrivalType.DELAY == purchaseOrder.getCenterArrivalInfo().centerArrivalType()) {
//            // [지연도착 알림톡 사용안함]
//            purchaseOrderAlimTalk.sendDelayArrivalTalk(purchaseOrder);
//        }
    }

    @Transactional // 입고검수중
    @Override
    public void persistWhenActivated(PurchaseOrder purchaseOrder) {
        InOrder inOrder = checkInOrder(purchaseOrder);
        checkItems(purchaseOrder.getOrderLineList());

        changeWorkStatusToActive(purchaseOrder, inOrder);
    }

    @Transactional
    @Override
    public void persistWhenCompleted(PurchaseOrder purchaseOrder) {
        InOrder inOrder = checkInOrderWhenCompleted(purchaseOrder);
        checkItems(purchaseOrder.getOrderLineList());

        purchaseOrder.complete(inOrder);

        completeInCheckProcess(purchaseOrder);
        completeInProcess(purchaseOrder);
        completeInWorkProcess(purchaseOrder);
        changeWorkStatusToCompleted(purchaseOrder);

        //Damaged 상품 고객확인 정보 생성
        updateDamagedItemList(purchaseOrder);
    }

    @Override
    public void sendNotificationWhenCompleted(PurchaseOrder purchaseOrder) {
        sendInCompleteTalk(purchaseOrder);    // 입고완료 알림톡 전송
        sendClientConfirmTalk(purchaseOrder); // 입고 Damaged 상품 고객확인 알림톡 전송
    }

    @Override
    public void persistWhenCancelled(PurchaseOrder purchaseOrder) {
        checkInOrder(purchaseOrder);

        purchaseOrder.cancel(); //입고취소

        changeWorkStatusToCancelled(purchaseOrder);
    }

    //입고완료 알림톡 전송
    @Transactional
    public void sendInCompleteTalk(PurchaseOrder purchaseOrder) {
        purchaseOrderAlimTalk.sendInCompleteTalk(purchaseOrder);
    }

    /**
     * 익일 출고 => 당일출고로 변환권유 조건
     * 1) 익일 출고건이여야 함
     * 2) 조기 도착이어야 함
     * 3) 도착시간이 09 ~ 13시여야 함
     */
    private boolean isAbleToBeChangedToPlusDayZero(CenterArrivalInfo info) {
        final LocalDateTime AT_09 = LocalDateTime.of(info.centerArrivalDateTime().toLocalDate(), LocalTime.of(9, 0));
        final LocalDateTime AT_13 = LocalDateTime.of(info.centerArrivalDateTime().toLocalDate(), LocalTime.of(13, 0));

        return ReleaseType.PLUS_DAY_1 == info.releaseType() //익일출고
                && CenterArrivalType.EARLY == info.centerArrivalType() //조기도착
                && info.centerArrivalDateTime().isAfter(AT_09) //09시~13시
                && info.centerArrivalDateTime().isBefore(AT_13);
    }

    /**
     * 입고검수 프로세스
     */
    private void completeInCheckProcess(PurchaseOrder purchaseOrder) {
        purchaseOrder.completeInCheckProcess(
                purchaseOrderRepository.getSlipNo(purchaseOrder.getWarehouseId().getValue(), IN_CHECK.getErpCode()) //입고검수 slipNo 생성
        );
        /* 입고검수 작업 정보 생성 insertBulkInCheck */
        purchaseOrderRepository.insertBulkInCheck(purchaseOrder);
    }

    /**
     * 입고완료 프로세스
     */
    public void completeInProcess(PurchaseOrder purchaseOrder) {
        purchaseOrder.completeInProcess(
                purchaseOrderRepository.getSlipNo(purchaseOrder.getWarehouseId().getValue(), IN.getErpCode()) //입고확정 slipNo 생성,
        );

        /* 입고완료 정보 생성 insertBulkIn */
        purchaseOrderRepository.insertBulkIn(purchaseOrder);
    }

    /**
     * 입고 작업 프로세스
     */
    private void completeInWorkProcess(PurchaseOrder purchaseOrder) {
        purchaseOrder.completeInWorkProcess();

        /* 입고 작업 정보 생성 insertInWrkInfo */
        purchaseOrderRepository.insertInWrkInfoWhenSales(purchaseOrder);
    }

}
