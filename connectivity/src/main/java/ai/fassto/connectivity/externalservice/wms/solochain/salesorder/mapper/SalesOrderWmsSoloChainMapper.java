package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.mapper;

import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit;
import ai.fassto.connectivity.domain.salesorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.Contact;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.Document;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.VehicleInfo;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.dto.SalesOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.ZERO_PADDED_FMT_HH_MM_SS_SSSS;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.*;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ESiteType.CUSTOMER;

@Component
public class SalesOrderWmsSoloChainMapper {
    public SalesOrderWmsSoloChainRequest salesOrderToCreateUpdateSalesOrderWmsSoloChainRequest(SalesOrder salesOrder) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.builder()
                .solhs_(toSolhs(salesOrder))
                .fileDocument_(toFileDocuments(salesOrder))
                .build()
        );

        return new SalesOrderWmsSoloChainRequest(transactions);
    }

    public SalesOrderWmsSoloChainRequest salesOrderToCreateUpdateSalesOrderWmsSoloChainRequest(List<SalesOrder> salesOrders) {
        List<Transaction> transactions = new ArrayList<>();
        for (SalesOrder salesOrder : salesOrders) {
            transactions.add(Transaction.builder().solhs_(toSolhs(salesOrder)).fileDocument_(toFileDocuments(salesOrder)).build());
        }
        return new SalesOrderWmsSoloChainRequest(transactions);
    }

    private List<FileDocument> toFileDocuments(SalesOrder salesOrder) {
        if (CollectionUtils.isEmpty(salesOrder.getDocumentList())) {
            return null;
        }

        return salesOrder.getDocumentList().stream().map(document -> toFileDocument(document, salesOrder)).toList();
    }

    private FileDocument toFileDocument(Document document, SalesOrder salesOrder) {
        return FileDocument.builder()
                .objectClass_(ObjectClass.builder()
                        .attr_(Attr.builder().className_(SOLH_).build())
                        .lifecycle_(OUTBOUND)
                        .orderClass_(salesOrder.getType().getOrderType().getSolochainType())
                        .orderNo_(salesOrder.getId().getValue())
                        .orderType_(new OrderType(salesOrder.getType().getType()))
                        .build()
                )
                .name_(document.source().getS3Path() + document.filePath() + document.fileName() + "." + document.fileExtension())
                .fileName_(document.source().getS3Path() + document.filePath() + document.fileName() + "." + document.fileExtension())
                .fileType_(new FileType(document.fileExtension().toUpperCase(Locale.ROOT)))
                .repository_(new Repository(AMAZON_S3_BUCKET))
                .documentType_(new DocumentType(AMAZON_S3_BUCKET))
                .build();
    }

    private List<SOLH> toSolhs(SalesOrder salesOrder) {
        List<SOLH> solhs = new ArrayList<>();
        solhs.add(toSolh(salesOrder));

        return solhs;
    }

    private SOLH toSolh(SalesOrder salesOrder) {
        return SOLH.builder()
                .lifecycle_(OUTBOUND)
                .orderClass_(salesOrder.getType().getOrderType().getSolochainType())
                .orderNo_(salesOrder.getId().getValue())
                .priority_(SalesOrder.DEFAULT_PRIORITY_FOR_SOLOCHAIN)
                .orderType_(new OrderType(salesOrder.getType().getType()))
                .requiredDate_(DateTimeUtil.localDateToString(salesOrder.getOrderDate(), DateTimeUtil.DATE_FORMATTER_yyyy_MM_dd) + ZERO_PADDED_FMT_HH_MM_SS_SSSS)
                .status_(toStatus(salesOrder.getActionType()))
                .supplier_(Supplier.builder()
                        .accountNo_(salesOrder.getWarehouseId().getValue())
                        .build()
                )
                .requester_(Requester.builder()
                        .accountNo_(salesOrder.getCustomer().getId().getValue())
                        .siteType_(SiteType.builder().name_(CUSTOMER.getSolochainName()).build())
                        .build()
                )
                .owner_(Owner.builder()
                        .accountNo_(salesOrder.getCustomer().getId().getValue())
                        .siteType_(SiteType.builder().name_(DOMAIN_CUSTOMER).build())
                        .build()
                )
                .customerBrandedBox_(salesOrder.getCustomerBrandedBox())
                .boxCategory_(salesOrder.getBoxCategory())
                .maxShipmentQuantity_(1)
                .referenceNo_(salesOrder.getOrderNo())
                .trackingNumber_(salesOrder.getVehicleInfo().trackingNo())
                .shipmentRequest_(ShipmentRequest.builder()
                        .referenceNo_(salesOrder.getId().getValue())
                        // .shipByDate_()
                        // .deliverByDate_()
                        .changeShippingOption_("Y")  // TMS에서 결정할 수 있게 함 - invoice 관련
                        .carrier_(StringUtils.isBlank(salesOrder.getVehicleInfo().companyCode()) ?
                                null :
                                new Carrier(salesOrder.getVehicleInfo().companyCode())
                        )
                        .shippingServiceLevel_(toShippingServiceLevel(salesOrder.getVehicleInfo()))
                        .shipFrom_(new ShipFrom(salesOrder.getWarehouseId().getValue()))
                        .requester_(Requester.builder()
                                .accountNo_(salesOrder.getCustomer().getId().getValue())
                                .siteType_(SiteType.builder().name_(CUSTOMER.getSolochainName()).build())
                                .build()
                        )
                        .shipTo_(ShipTo.builder()
                                .accountNo_(salesOrder.getCustomer().getId().getValue())
                                .siteType_(SiteType.builder().name_(CUSTOMER.getSolochainName()).build())
                                .build()
                        )
                        .shipToName_(salesOrder.getShipToContact().name())
                        .shipToPhoneNo_(salesOrder.getShipToContact().telephoneNo())
                        .shipToPhoneNo2_(salesOrder.getShipToContact().telephoneNoEtc())
                        .shipToAddress_(PrimaryAddress.builder()
                                .address1_(salesOrder.getShipToContact().address1())
                                .address2_(salesOrder.getShipToContact().address2())
                                .postalCode_(StringUtils.isBlank(salesOrder.getShipToContact().zipCode()) ? "12345" : salesOrder.getShipToContact().zipCode())
                                .city_("Seoul")
                                .province_("Seoul")
                                .country_("KR")
                                .build()
                        )
                        .shipToInstruction_(salesOrder.getShipToContact().instruction())
                        .shipToPassword_(salesOrder.getShipToContact().frontDoorPassword())
                        .senderPhoneNumber_(salesOrder.getSenderContact().telephoneNo())
                        .build()
                )
                .orderInstruction_(toOrderInstructionList(salesOrder.getSenderContact()))
                .getSOLIByParent_(GetSOLIByParent.builder()
                        .attr_(Attr.builder().action_(OVERRIDE).parameter_(PARENT).build())
                        .soli_(toSolis(salesOrder))
                        .build()
                )
                .build();
    }

    private ShippingServiceLevel toShippingServiceLevel(VehicleInfo vehicleInfo) {
        if (StringUtils.isBlank(vehicleInfo.shippingServiceLevelCode()) ||
                StringUtils.isBlank(vehicleInfo.shippingServiceLevelName()) ||
                StringUtils.isBlank(vehicleInfo.companyCode())
        ) {
            return null;
        }

        return ShippingServiceLevel.builder()
                .code_(vehicleInfo.shippingServiceLevelCode())
                .name_(vehicleInfo.shippingServiceLevelName())
                .carrier_(new Carrier(vehicleInfo.companyCode()))
                .build();
    }

    private List<SOLI> toSolis(SalesOrder salesOrder) {
        List<SOLI> solis = new ArrayList<>();
        List<OrderLine> orderLineList = salesOrder.getOrderLineList();

        for (OrderLine orderLine : orderLineList) {
            solis.add(toSoli(orderLine, salesOrder));
        }

        return solis;
    }

    private SOLI toSoli(OrderLine orderLine, SalesOrder salesOrder) {
        return SOLI.builder()
                .sequence_(String.valueOf(orderLine.getId().getValue()))
                .shipmentRequest_(ShipmentRequestSimple.builder()
                        .attr_(Attr.builder().className_(SHIPMENT_REQUEST).index_(BY_REFERENCE_AND_REFERENCE_NO).build())
                        .referenceNo_(salesOrder.getId().getValue())
                        .solh_(SOLHSimple.builder()
                                .lifecycle_(OUTBOUND)
                                .orderClass_(salesOrder.getType().getOrderType().getSolochainType())
                                .orderNo_(salesOrder.getId().getValue())
                                .build()
                        )
                        .shipFrom_(new ShipFrom(salesOrder.getWarehouseId().getValue()))
                        .build()
                )
                .status_(toStatus(salesOrder.getActionType()))
                .materialMaster_(MaterialMaster.builder().partNo_(orderLine.getItemSimple().getId().getValue()).build())
                .uoi_(UOI.builder().name_(ItemUnit.EA.name()).build())
                .quantity_(orderLine.getQuantityInfo().orderQuantity())
                .owner_(Owner.builder()
                        .accountNo_(salesOrder.getCustomer().getId().getValue())
                        .siteType_(SiteType.builder().name_(CUSTOMER.getSolochainName()).build())
                        .build()
                )
                .condition_(toCondition(orderLine))
                .shipTo_(ShipTo.builder()
                        .accountNo_(salesOrder.getCustomer().getId().getValue())
                        .siteType_(SiteType.builder().name_(CUSTOMER.getSolochainName()).build())
                        .build()
                )
                .soliDimension_(toSOLIDimension(orderLine))
                .build();
    }

    private SOLIDimension toSOLIDimension(OrderLine orderLine) {
        if (StringUtils.isBlank(orderLine.getLotNo())) {
            return null;
        }

        return SOLIDimension.builder()
                .dimension_(toDimension(orderLine))
                .quantity_(String.valueOf(orderLine.getQuantityInfo().orderQuantity()))
                .uoi_(toUOI(orderLine))
                .mode_(HARD)
                .build();
    }

    private UOI toUOI(OrderLine orderLine) {
        return UOI.builder()
                .materialMaster_(MaterialMaster.builder().partNo_(orderLine.getItemSimple().getId().getValue()).build())
                .name_(ItemUnit.EA.name())
                .build();
    }

    private Dimension toDimension(OrderLine orderLine) {
        return Dimension.builder()
                .attr_(Attr.builder().className_(MMLOT_NUMBER).index_(BY_MATERIAL_MASTER_VALUE).build())
                .materialMaster_(MaterialMaster.builder().partNo_(orderLine.getItemSimple().getId().getValue()).build())
                .value_(orderLine.getLotNo())
                .build();
    }

    private Condition toCondition(OrderLine orderLine) {
        if (StringUtils.isBlank(orderLine.getCondition())) {
            return null;
        }

        return Condition.builder()
                .attr_(Attr.builder().className_(SoloChainConstant.MATERIAL_CONDITION).index_(SoloChainConstant.BY_NAME).build())
                .name(orderLine.getCondition())
                .build();
    }


    private String toStatus(ActionType actionType) {
        return actionType.isDelete() ? SalesOrderStatus.CANCELLED.name() : SalesOrderStatus.ACTIVE.name();
    }

    /**
     * newRemark: 비고 -> SenderContact -> Instruction
     * picking, packing, shipping 별로 나누어져 있지않아서 한꺼번에 적용시킴
     */
    private List<OrderInstruction> toOrderInstructionList(Contact senderContact) {
        if (StringUtils.isBlank(senderContact.instruction()) && StringUtils.isBlank(senderContact.packingInstruction())) {
            return null;
        }

        List<OrderInstruction> orderInstructionList = new ArrayList<>();
        String defaultInstruction = StringUtils.isBlank(senderContact.instruction()) ? senderContact.packingInstruction() : senderContact.instruction();
        orderInstructionList.add(new OrderInstruction("Picking", StringUtils.isBlank(senderContact.pickingInstruction()) ? defaultInstruction : senderContact.pickingInstruction()));
        orderInstructionList.add(new OrderInstruction("Packing", StringUtils.isBlank(senderContact.packingInstruction()) ? defaultInstruction : senderContact.packingInstruction()));
        orderInstructionList.add(new OrderInstruction("Shipping", StringUtils.isBlank(senderContact.shippingInstruction()) ? defaultInstruction : senderContact.shippingInstruction()));

        return orderInstructionList;
    }
}
