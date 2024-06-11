package ai.fassto.connectivity.domain.purchaseorder.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Item;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.QuantityInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderStatus;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit.EA;

@Component
public class ErpPurchaseOrderDataMapper {

    public PurchaseOrder erpPurchaseOrderRequestToPurchaseOrder(UpdateErpPurchaseOrderRequest request) {
        return PurchaseOrder.Builder.builder()
                .id(new PurchaseOrderId(request.getSlipNo()))
                .type(PurchaseOrderType.findByType(request.getOrderType()))
                .status(PurchaseOrderStatus.findBy(request.getOrderStatus()))
                .receivedDateTime(request.getReceivedDate())
                .warehouseId(new WarehouseId(request.getWarehouseCode()))
                .customerId(new CustomerId(request.getCustomerCode()))
                .customHeaderCheckListInfo(request.getCustomHeaderCheckListInfo())
                .workerQty(request.getWorkerQty())
                .workTime(request.getWorkTime())
                .orderLineList(toOrderLineList(request.getOrderLines()))
                .itemList(toItems(request.getItems()))
                .build();
    }

    public UpdateErpPurchaseOrderResponse purchaseOrderToUpdateErpPurchaseOrderResponse(PurchaseOrder purchaseOrder) {
        return new UpdateErpPurchaseOrderResponse(purchaseOrder.getId().getValue());
    }

    private List<Item> toItems(List<ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.Item> itemList) {
        return itemList == null ? null : itemList.stream().map(this::toItem).toList();
    }

    private Item toItem(ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.Item item) {
        return Item.Builder.builder()
                .id(new ItemId(item.getCode()))
                .receiptNo(item.getReceiptNo())
                .orderLineId(new OrderLineId(item.getSequence()))
                .lotNo(item.getLotNo())
                .condition(item.getCondition())
                .memo(item.getMemo())
                .imagePath(item.getImagePath())
                .makeDate(item.getMakeDate() == null ? null : item.getMakeDate().toLocalDate())
                .expiryDate(item.getExpiryDate() == null ? null : item.getExpiryDate().toLocalDate())
                .uoi(item.getUoi_())
                .quantity(item.getQty())
                .baseQuantity(item.getBaseQuantity())
                .barcodeQuantity(item.getBarcodeQty())
                .build();
    }

    private List<OrderLine> toOrderLineList(List<ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.OrderLine> orderLines) {
        if (orderLines != null && orderLines.size() > 0) {
            return orderLines.stream().map(this::toOrderLine).toList();
        }

        return new ArrayList<>();
    }

    private OrderLine toOrderLine(ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.OrderLine orderLine) {
        return OrderLine.Builder.builder()
                .id(new OrderLineId(orderLine.getSequence()))
                .itemSimple(ItemSimple.Builder.builder().id(new ItemId(orderLine.getCode())).build())
                .quantityInfo(toQuantityInfo(orderLine))
                .build();
    }

    private QuantityInfo toQuantityInfo(ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.OrderLine orderLine) {
        return QuantityInfo.builder()
                .itemUnit(EA)
                .orderQuantity(orderLine.getOrderQty())
                .receivedQuantity(orderLine.getReceivedQty())
                .remainingQuantity(orderLine.getRemainingQty())
                .cancelledQuantity(orderLine.getCancelledQty())
                .build();
    }


}
