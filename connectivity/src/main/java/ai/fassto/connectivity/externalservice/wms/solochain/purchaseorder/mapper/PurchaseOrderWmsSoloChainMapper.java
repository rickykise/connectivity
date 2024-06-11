package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.mapper;

import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.dto.PurchaseOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.valueobject.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyy_MM_dd;
import static ai.fassto.connectivity.common.utility.DateTimeUtil.ZERO_PADDED_FMT_HH_MM_SS_SSSS;
import static ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit.EA;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.*;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ESiteType.CUSTOMER;

@Component
public class PurchaseOrderWmsSoloChainMapper {
    public PurchaseOrderWmsSoloChainRequest purchaseOrderToPurchaseOrderWmsSoloChainRequest(PurchaseOrder purchaseOrder) {
        List<Transaction> transactions = new ArrayList<>();
        List<SOLH> solhs = new ArrayList<>();

        solhs.add(toSolh(purchaseOrder.getOrderLineList().get(0), purchaseOrder));
        transactions.add(new Transaction(solhs));

        return new PurchaseOrderWmsSoloChainRequest(transactions);
    }

    private SOLH toSolh(OrderLine orderLine, PurchaseOrder purchaseOrder) {
        return SOLH.builder()
                .lifecycle_(INBOUND)
                .orderClass_(purchaseOrder.getType().getOrderType().getSolochainType())
                .orderNo_(purchaseOrder.getId().getValue())
                .priority_(StringUtils.isBlank(orderLine.getReleaseTypeName()) ? null : orderLine.getReleaseTypeName())
                .orderType_(new OrderType(purchaseOrder.getType().getType()))
                .requiredDate_(DateTimeUtil.toStringBy(orderLine.getOrderDate(), DATE_FORMATTER_yyyy_MM_dd) + ZERO_PADDED_FMT_HH_MM_SS_SSSS)
                .status_(toStatus(purchaseOrder.getActionType()))
                .supplier_(toSupplier(orderLine))
                .requester_(Requester.builder().accountNo_(orderLine.getWarehouseId().getValue()).build())
                .orderInstruction_(toOrderInstructions(orderLine.getVehicleInfo().memo())) // SoloChainConstant.RECEIVING, "100%"
                .trackingNumber_(orderLine.getVehicleInfo().trackingNo())
                .vehicleType_(orderLine.getVehicleInfo().vehicleTypeName())
                .originalCarrier_(orderLine.getVehicleInfo().originalCarrier())
                .originalTrackingNumber_(orderLine.getVehicleInfo().originalTrackingNumber())
                .returnReason_(orderLine.getVehicleInfo().returnReasonName())
                .inboundTransportCompany_(orderLine.getVehicleInfo().inboundTransportCompany())
                .inboundLicensePlate_(orderLine.getVehicleInfo().inboundLicensePlate())
                .driverContact_(orderLine.getVehicleInfo().driverContact())
                //.memo_(orderLine.getVehicleInfo().memo())
                .getSOLIByParent_(new GetSOLIByParent(
                        Attr.builder().action_(OVERRIDE).parameter_(PARENT).build(),
                        toSolis(purchaseOrder)
                ))
                .build();
    }

    private Supplier toSupplier(OrderLine orderLine) {
        return Supplier.builder()
                .accountNo_(orderLine.getCustomer().getId().getValue())
                .siteType_(new SiteType(CUSTOMER.getSolochainName()))
                .build();
    }

    private List<OrderInstruction> toOrderInstructions(String memo) {
        if (StringUtils.isBlank(memo)) {
            return null;
        }

        return List.of(new OrderInstruction(SoloChainConstant.RECEIVING, memo));
    }

    private List<SOLI> toSolis(PurchaseOrder purchaseOrder) {
        return purchaseOrder.getOrderLineList().stream()
                .map(ol -> toSoli(ol, purchaseOrder.getActionType()))
                .toList();
    }

    private SOLI toSoli(OrderLine orderLine, ActionType actionType) {
        return SOLI.builder()
                .sequence_(String.valueOf(orderLine.getId().getValue()))
                .status_(toStatus(actionType))
                .materialMaster_(new MaterialMaster(orderLine.getItemSimple().getId().getValue()))
                .uoi_(UOI.builder().name_(EA.name()).build())
                .quantity_(orderLine.getQuantityInfo().orderQuantity().toString())
                .owner_(new Owner(
                        orderLine.getCustomer().getId().getValue(),
                        new SiteType(CUSTOMER.getSolochainName())
                ))
                .shipTo_(ShipTo.builder().accountNo_(orderLine.getWarehouseId().getValue()).build())
                .build();
    }

    private String toStatus(ActionType actionType) {
        return actionType.isDelete() ? PurchaseOrderStatus.CANCELLED.name() : PurchaseOrderStatus.STDBY.name();
    }


}
