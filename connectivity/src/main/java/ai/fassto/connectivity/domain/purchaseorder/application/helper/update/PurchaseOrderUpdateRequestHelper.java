package ai.fassto.connectivity.domain.purchaseorder.application.helper.update;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.PurchaseOrderId;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.notification.alimtalk.PurchaseOrderAlimTalk;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.repository.PurchaseOrderRepository;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Item;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderItemDisabledException;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderItemNotFoundException;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderNotCenterArrivalException;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderNotFoundException;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public abstract class PurchaseOrderUpdateRequestHelper {
    protected final PurchaseOrderRepository purchaseOrderRepository;
    protected final PurchaseOrderAlimTalk purchaseOrderAlimTalk;

    public abstract boolean is(OrderType orderType);

    public abstract void persistWhenArrived(PurchaseOrder purchaseOrder);

    public abstract void sendNotificationWhenArrived(PurchaseOrder purchaseOrder);

    public abstract void persistWhenActivated(PurchaseOrder purchaseOrder);

    public abstract void persistWhenCompleted(PurchaseOrder purchaseOrder);

    public abstract void sendNotificationWhenCompleted(PurchaseOrder purchaseOrder);

    public abstract void persistWhenCancelled(PurchaseOrder purchaseOrder);

    // 입고|반품 Damaged 상품 고객확인 알림톡 전송
    @Transactional
    public void sendClientConfirmTalk(PurchaseOrder purchaseOrder){
        if (purchaseOrder.getDamagedItemList().size() > 0) {
            purchaseOrderAlimTalk.sendClientConfirmTalk(purchaseOrder);
        }
    }

    /**
     * 입고/ 반품 상태 완료로 변경
     */
    public void changeWorkStatusToCompleted(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.updateOrderStatus(purchaseOrder); //입고검수중(2) -> 입고완료(4)
    }

    InOrder checkInOrder(PurchaseOrder purchaseOrder) {
        Optional<InOrder> inOrderOptional = purchaseOrderRepository.findOneInOrdOptional(purchaseOrder);
        if (inOrderOptional.isEmpty()) {
            throw new PurchaseOrderNotFoundException(new ErrorDetail("slipNo", purchaseOrder.getId().getValue()));
        }

        return inOrderOptional.get();
    }

    InOrder checkInOrderWhenCompleted(PurchaseOrder purchaseOrder) {
        InOrder inOrder = checkInOrder(purchaseOrder);

        if (inOrder.centerArrivalType() == null) {
            throw new PurchaseOrderNotCenterArrivalException(new ErrorDetail("centerArrivalType", "null"));
        }

        return inOrder;
    }

    void checkItems(List<OrderLine> orderLineList) {
        if (orderLineList == null || orderLineList.size() == 0) {
            return;
        }

        List<ItemId> itemsIdList = orderLineList.stream().map(orderLine -> orderLine.getItemSimple().getId()).toList();
        List<ItemSimple> foundItemList = purchaseOrderRepository.findItemSimplesBy(itemsIdList);
        Map<ItemId, ItemSimple> foundItemMap = foundItemList.stream().collect(Collectors.toMap(ItemSimple::getId, Function.identity()));

        for (ItemId id : itemsIdList) {
            ItemSimple itemSimple = foundItemMap.get(id);
            if (itemSimple == null) {
                throw new PurchaseOrderItemNotFoundException(new ErrorDetail("itemId", id.getValue()));
            } else if (!itemSimple.getEnabled()) {
                throw new PurchaseOrderItemDisabledException(new ErrorDetail("itemId", id.getValue()));
            }
        }
    }

    public void changeWorkStatusToActive(PurchaseOrder purchaseOrder, InOrder inOrder) {
        /* 입고 작업 상태 변경 UpdateWrkStat */
        purchaseOrder.activate(inOrder);
        purchaseOrderRepository.updateOrderStatus(purchaseOrder); //입고예정(1) -> 입고검수중(2)
    }

    public void changeWorkStatusToCancelled(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.updateOrderStatus(purchaseOrder);
    }

    private Map<String, Item> getFoundDamagedItemMap(PurchaseOrderId purchaseOrderId) {
        /* Update New Damaged Item List */
        /* 기존 Damaged 상품목록 조회 findDamagedItemListBySlipNo */
        return purchaseOrderRepository.findDamagedItemListBy(purchaseOrderId).stream()
                .collect(Collectors.toMap(Item::getReceiptNo, Function.identity()));
    }

    //Damaged 상품 고객확인 정보 생성
    public void updateDamagedItemList(PurchaseOrder purchaseOrder) {
        /*
         *  find requested damaged item not in db
         */
        Integer listSize = purchaseOrder.updateNewDamagedItemListAndGetListSize(getFoundDamagedItemMap(purchaseOrder.getId()));
        if (listSize > 0) {
            /* Damaged 상품 고객확인 정보 생성 insertBulkDamagedItemClientConfirm */
            purchaseOrderRepository.insertBulkDamagedItemClientConfirm(purchaseOrder);

        }

        //불량 상품의 Memo 정보가 있을때 만 고객확인 Y update (mvp에서 Memo정보 못 받음, grand open 대비용)
        Integer newListSize = purchaseOrder.updateNewDamagedItemListWhenMomoNotBlackAndGetListSize();
        if (newListSize > 0) {
            /* 고객확인여부 Y로 변경 updateBulkClientConfirmYn */
            purchaseOrderRepository.updateBulkClientConfirmYn(purchaseOrder);

        }
    }


}
