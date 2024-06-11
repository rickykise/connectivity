package ai.fassto.connectivity.domain.salesorder.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.core.entity.*;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.QuantityInfo;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Component
public class ErpSalesOrderDataMapper {

    public SalesOrder erpSalesOrderRequestToSalesOrder(UpdateErpSalesOrderRequest request) {
        return SalesOrder.Builder.builder()
                .id(new SalesOrderId(request.getSlipNo()))
                .type(SalesOrderType.findByType(request.getOrderType()))
                .status(SalesOrderStatus.findBySolochainStatus(request.getOrderStatus()))
                .workDate(request.getWorkDate())
                .warehouseId(new WarehouseId(request.getWarehouseCode()))
                .customer(Customer.Builder.builder().id(new CustomerId(request.getCustomerCode())).build())
                .orderLineList(toErpOrderLineList(request.getOrderLines()))
                .items(toItems(request.getItems()))
                .consumables(toConsumables(request.getConsumables()))
                .boxes(toBoxes(request.getBoxes()))
                .build();
    }

    private List<Box> toBoxes(List<ai.fassto.connectivity.domain.salesorder.application.dto.Box> boxes) {
        if (CollectionUtils.isEmpty(boxes)) {
            return new ArrayList<>();
        }

        return boxes.stream().map(v -> Box.builder()
                        .boxSeq(v.getBoxSeq())
                        .boxId(v.getBoxId())
                        .boxType(v.getBoxType())
                        .category(v.getCategory())
                        .courierCode(v.getCourierCode())
                        .invoiceNo(v.getInvoiceNo())
                        .basicFare(v.getBasicFare())
                        .dealFare(v.getDealFare())
                        .airFare(v.getAirFare())
                        .shipFare(v.getShipFare())
                        .realPackingVideo(v.getRealPackingVideo())
                        .build()
                )
                .toList();
    }

    private List<Consumable> toConsumables(List<ai.fassto.connectivity.domain.salesorder.application.dto.Consumable> consumables) {
        if (CollectionUtils.isEmpty(consumables)) {
            return new ArrayList<>();
        }
        return consumables.stream().map(consumable ->
                Consumable.Builder.builder()
                                .sequence(consumable.getSequence())
                                .code(consumable.getCode())
                                .qty(consumable.getQty())
                                .size(consumable.getSize())
                                .type(consumable.getType())
                                .build()
        ).toList();
    }

    private List<Item> toItems(List<ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return new ArrayList<>();
        }

        return items.stream().map(v -> Item.builder()
                .code(v.getCode())
                .sequence(v.getSequence())
                .qty(StringUtils.isBlank(v.getQty()) ? null : Integer.valueOf(v.getQty()))
                .loc("")            //LocNo 솔로체인에서 주지않음
                .lu("NONE")         //NONE 고정
                .lot(StringUtils.isBlank(v.getLot()) ? null : v.getLot())
                .expiryDate(v.getExpiryDate() == null ? null : v.getExpiryDate().toLocalDate())
                .boxSeq(v.getBoxSeq())
                .build()
        ).toList();
    }

    public UpdateErpSalesOrderResponse salesOrderToUpdateErpSalesOrderResponse(SalesOrder salesOrder) {
        return new UpdateErpSalesOrderResponse(
                salesOrder.getId().getValue(),
                salesOrder.getOrderNo(),
                salesOrder.getCustomer().getId().getValue()
        );
    }


    private List<OrderLine> toErpOrderLineList(List<ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.OrderLine> orderLines) {
        if (orderLines != null && orderLines.size() > 0) {
            return orderLines.stream().map(this::toOrderLine).toList();
        }

        return new ArrayList<>();
    }

    private OrderLine toOrderLine(ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.OrderLine orderLine) {
        return OrderLine.Builder.builder()
                .id(new OrderLineId(orderLine.getSequence()))
                .itemSimple(ItemSimple.Builder.builder().id(new ItemId(orderLine.getCode())).build())
                .quantityInfo(toQuantityInfo(orderLine))
                .build();
    }

    private QuantityInfo toQuantityInfo(ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.OrderLine orderLine) {
        return QuantityInfo.builder()
                .orderQuantity(orderLine.getQuantity())
                .allocatedQuantity(orderLine.getAllocatedQty())
                .pickQuantity(orderLine.getPickedQty())
                .packQuantity(orderLine.getPackQty())
                .shippedQty(orderLine.getShippedQty())
                .cancelledQty(orderLine.getCancelledQty())
                .build();
    }

}
