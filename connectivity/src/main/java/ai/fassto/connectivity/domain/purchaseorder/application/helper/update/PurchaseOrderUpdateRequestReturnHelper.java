package ai.fassto.connectivity.domain.purchaseorder.application.helper.update;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.notification.alimtalk.PurchaseOrderAlimTalk;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.repository.PurchaseOrderRepository;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderNotSupportException;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static ai.fassto.connectivity.dataaccess.common.valueobject.SlipNoDivType.IN;

@Slf4j
@Component
public class PurchaseOrderUpdateRequestReturnHelper extends PurchaseOrderUpdateRequestHelper{
    private final OrderType ORDER_TYPE = OrderType.RETURN;

    public PurchaseOrderUpdateRequestReturnHelper(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderAlimTalk purchaseOrderAlimTalk) {
        super(purchaseOrderRepository, purchaseOrderAlimTalk);
    }

    @Override
    public boolean is(OrderType orderType) {
        return ORDER_TYPE.equals(orderType);
    }

    @Override
    public void persistWhenArrived(PurchaseOrder purchaseOrder) {
        throw new PurchaseOrderNotSupportException(new ErrorDetail("Status when Return this status is not supported.", purchaseOrder.getStatus().name()));
    }

    @Override
    public void sendNotificationWhenArrived(PurchaseOrder purchaseOrder) {
        throw new PurchaseOrderNotSupportException(new ErrorDetail("Status when Return this status is not supported.", purchaseOrder.getStatus().name()));
    }

    @Transactional // 반품검수중
    @Override
    public void persistWhenActivated(PurchaseOrder purchaseOrder) {
        InOrder inOrder = checkInOrder(purchaseOrder);
        checkItems(purchaseOrder.getOrderLineList());

        changeWorkStatusToActive(purchaseOrder, inOrder);
    }


    @Transactional // 반품완료
    @Override
    public void persistWhenCompleted(PurchaseOrder purchaseOrder) {
        InOrder inOrder = checkInOrder(purchaseOrder);
        checkItems(purchaseOrder.getOrderLineList());

        purchaseOrder.completeWhenReturn(inOrder);

        /* 반품 작업 정보 생성 insertInWrkInfo */
        completeInWorkWhenReturnProcess(purchaseOrder, inOrder);
        completeInWhenReturnProcess(purchaseOrder);
        changeWorkStatusToCompleted(purchaseOrder);

        //Damaged 상품 고객확인 정보 생성
        updateDamagedItemList(purchaseOrder);
    }

    @Override
    public void sendNotificationWhenCompleted(PurchaseOrder purchaseOrder) {
        sendClientConfirmTalk(purchaseOrder);   //반품 Damaged 상품 고객확인 알림톡 전송
    }

    @Override
    public void persistWhenCancelled(PurchaseOrder purchaseOrder) {
        checkInOrder(purchaseOrder);

        purchaseOrder.cancel(); //반품 입고취소

        changeWorkStatusToCancelled(purchaseOrder);
    }

    /**
     * 반품완료 프로세스
     */
    public void completeInWhenReturnProcess(PurchaseOrder purchaseOrder) {
        purchaseOrder.completeInWhenReturnProcess(
                purchaseOrderRepository.getSlipNo(purchaseOrder.getWarehouseId().getValue(), IN.getErpCode()) //입고확정 slipNo 생성,
        );

        /* 반품완료 정보 생성 insertBulkInWhenReturn */
        purchaseOrderRepository.insertBulkInWhenReturn(purchaseOrder);
    }

    /**
     * 반품작업 프로세스
     */
    private void completeInWorkWhenReturnProcess(PurchaseOrder purchaseOrder, InOrder inOrder) {
        purchaseOrder.completeInWorkWhenReturnProcess(inOrder);

        /* 반품 작업 정보 생성 insertInWrkInfoWhenReturn */
        purchaseOrderRepository.insertInWrkInfoWhenReturn(purchaseOrder);
    }

}
