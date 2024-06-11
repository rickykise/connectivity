package ai.fassto.connectivity.domain.purchaseorder.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.PurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Customer;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.QuantityInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.VehicleInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.VehicleType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit.EA;

@Component
public class SolochainPurchaseOrderDataMapper {
    public PurchaseOrder soloChainPurchaseOrderRequestToPurchaseOrder(SoloChainPurchaseOrderRequest request, ActionType actionType) {
        return PurchaseOrder.Builder.builder()
                .id(new PurchaseOrderId(request.getDataList().get(0).getSlipNo()))
                .type(PurchaseOrderType.findByType(request.getOrderType()))
                .warehouseId(new WarehouseId(request.getDataList().get(0).getWhCd()))
                .orderLineList(dataListtoOrderLineList(request))
                .actionType(actionType)
                .build();
    }

    public SoloChainPurchaseOrderResponse purchaseOrderToSoloChainPurchaseOrderResponse(PurchaseOrder purchaseOrder) {
        return new SoloChainPurchaseOrderResponse(
                purchaseOrder.getId().getValue(),
                purchaseOrder.getOrderLineList().stream()
                        .map(item -> item.getItemSimple().getId().getValue())
                        .toList()
        );
    }

    private List<OrderLine> dataListtoOrderLineList(SoloChainPurchaseOrderRequest request) {
        List<OrderLine> orderLineList = new ArrayList<>();
        PurchaseOrderType purchaseOrderType = PurchaseOrderType.findByType(request.getOrderType());

        for (var purchaseOrder : request.getDataList()) {
            orderLineList.add(toPurchaseOrderLine(purchaseOrder, purchaseOrderType));
        }

        return orderLineList;
    }

    private OrderLine toPurchaseOrderLine(PurchaseOrderRequest request, PurchaseOrderType purchaseOrderType) {
        return OrderLine.Builder.builder()
                .id(new OrderLineId(request.getItemSeq()))
                .customer(new Customer(new CustomerId(request.getCstCd())))
                .itemSimple(ItemSimple.Builder.builder()
                        .id(new ItemId(request.getGodCd()))
                        .build()
                )
                .orderDate(request.getOrdDt())
                .warehouseId(new WarehouseId(request.getWhCd()))
                .quantityInfo(QuantityInfo.builder()
                        .itemUnit(EA)
                        .orderQuantity(
                                purchaseOrderType.getOrderType() == OrderType.RETURN ? request.getRtnQty() : request.getOrdQty()
                        )
                        .build()
                )
                .vehicleInfo(VehicleInfo.builder()
                        .trackingNo(request.getParcelInvoiceNo()) // 송장번호 : "FEDEX1234567",
                        .vehicleType(request.getInWay()) // 01: 택배(Courier), 02: 차량(Vehicle)
                        .vehicleTypeName(request.getInWayName()) // courier, vehicle
                        .originalCarrier(request.getOrgParcelComp())
                        .originalTrackingNumber(
                                purchaseOrderType == PurchaseOrderType.CONSUMER_RETURN_ORDER ?
                                        request.getInvoiceNo() : request.getOrgInvoiceNo()
                        )
                        .returnReason(request.getRtnGubun())
                        .returnReasonName(request.getRtnGubunName())
                        .inboundTransportCompany(toInboundTransportCompanyBy(request, purchaseOrderType))
                        .inboundLicensePlate(request.getCarNumber())
                        .driverContact(request.getCourierDriverPhoneNumber())
                        .memo(
                                // consumerReturnOrder and courier
                                purchaseOrderType == PurchaseOrderType.CONSUMER_RETURN_ORDER && VehicleType.isCourierByErpCode(request.getInWay()) ?
                                        request.getShipReqTerm() : request.getRemark()
                        )
                        .build()
                )
                .releaseType(request.getReleaseGbn())
                .releaseTypeName(request.getReleaseGbName())
                .vendorCode(request.getSupCd())
                .build();
    }

    private String toInboundTransportCompanyBy(PurchaseOrderRequest request, PurchaseOrderType purchaseOrderType) {
        if (VehicleType.isCourierByErpCode(request.getInWay())) {
            if (purchaseOrderType == PurchaseOrderType.CONSUMER_RETURN_ORDER) {
                return request.getParcelCd();
            }
            return request.getParcelComp();
        }
        return request.getCourierName();
    }
}
