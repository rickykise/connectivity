package ai.fassto.connectivity.domain.salesorder.application.helper.update;

import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.salesorder.application.port.output.repository.SalesOrderRepository;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SalesOrderUpdateRequestSalesHelper extends SalesOrderUpdateRequestHelper{
    private final OrderType ORDER_TYPE = OrderType.SALES; //출고

    public SalesOrderUpdateRequestSalesHelper(SalesOrderRepository salesOrderRepository) {
        super(salesOrderRepository);
    }

    @Override
    public boolean is(OrderType orderType) {
        return ORDER_TYPE.equals(orderType);
    }

    @Transactional //출고지시확정
    @Override
    public void persistWhenAllocated(SalesOrder salesOrder) {
        OutOrder outOrder = checkOutOrder(salesOrder);
        checkItems(salesOrder.getOrderLineList());
        checkInventoryAssign(salesOrder, outOrder);

        salesOrder.allocate(outOrder);


        processForOutPickMapWhenAllocated(salesOrder);
        processForOutInventoryAssignWhenAllocated(salesOrder);
        processForOutOrderSetWhenAllocated(salesOrder);
        processForOutPickOrderWhenAllocated(salesOrder);
        changeWorkStatusOutOrder(salesOrder);
    }

    @Transactional //피킹
    @Override
    public void persistWhenPickingCompleted(SalesOrder salesOrder) {
        OutOrder outOrder = checkOutOrder(salesOrder);
        checkItems(salesOrder.getOrderLineList());
        checkInventoryAssign(salesOrder, outOrder);

        //출고지시확정시(ALLOCATED) LotNo를 받는경우 true
        boolean isExist = isExistOutPickOrder(salesOrder);

        salesOrder.completePicking(outOrder, isExist);

        processForOutOrderSetWhenPicked(salesOrder);
        processForOutPickOrderWhenPicked(salesOrder, isExist);
    }

    @Transactional //패킹
    @Override
    public void persistWhenPackingCompleted(SalesOrder salesOrder) {
        OutOrder outOrder = checkOutOrder(salesOrder);
        checkItems(salesOrder.getOrderLineList());
        checkInventoryAssign(salesOrder, outOrder);
        checkBoxes(salesOrder);

        salesOrder.completePacking(outOrder);

        processForOutPickOrderWhenPackedOrShipped(salesOrder);
        processForOutPackWhenPacked(salesOrder);
        processForOutOrderWhenPacked(salesOrder, outOrder);
    }

    @Transactional // 출고
    @Override
    public void persistWhenShippingCompleted(SalesOrder salesOrder) {
        OutOrder outOrder = checkOutOrder(salesOrder);
        checkItems(salesOrder.getOrderLineList());
        checkInventoryAssign(salesOrder, outOrder);
        checkBoxes(salesOrder);
        checkConsumables(salesOrder);
        salesOrder.seperatedItems(separateGod(salesOrder));

        salesOrder.completeShipping(outOrder, generateOutSlipNo(salesOrder));

        processForOutPickOrderWhenPackedOrShipped(salesOrder);
        processForOutWhenShipped(salesOrder);
        processForOutInvoiceWhenShipped(salesOrder);
        processForOutPackWhenShipped(salesOrder);
        processForOutOrderWhenShipped(salesOrder, outOrder);
        processForOutConsumableWhenShipped(salesOrder);
    }

    @Transactional  //출고지시확정 취소
    @Override
    public void persistWhenCancelAllocated(SalesOrder salesOrder) {
        OutOrder outOrder = checkOutOrder(salesOrder);

        // 존재여부 체크(outOrdSlipNo 기준)
        checkOutPickMap(salesOrder);

        salesOrder.allocateCancel(outOrder);

        processForOutPickMapWhenCancelAllocated(salesOrder);
        processForOutOrderSetWhenCancelAllocated(salesOrder);
        processForOutPickOrderWhenCancelAllocated(salesOrder);
        processForOutPackWhenCancelAllocated(salesOrder);
        processForOutWhenCancelAllocated(salesOrder);
        processForOutInvoiceWhenCancelAllocated(salesOrder);
        processForOutOrderWhenCancelAllocated(salesOrder);
        processForOutConsumableWhenCancelAllocated(salesOrder);
    }

}
