package ai.fassto.connectivity.domain.salesorder.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.VehicleType;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.*;
import ai.fassto.connectivity.domain.salesorder.core.entity.Customer;
import ai.fassto.connectivity.domain.salesorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.salesorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.Contact;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.Document;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.QuantityInfo;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.VehicleInfo;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.DocumentSource;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderType;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Component
public class SolochainSalesOrderDataMapper {
    public SalesOrder soloChainSalesOrderRequestToSalesOrder(SoloChainSalesOrderRequest request, ActionType actionType) {
        OrderData orderData = request.getOrderData();

        return SalesOrder.Builder.builder()
                .id(new SalesOrderId(orderData.getSlipNo()))
                .type(SalesOrderType.findByType(orderData.getOrderType()))
                .orderLineList(toOrderLineList(request.getGodList()))
                .shipToContact(toShipToContact(orderData))
                .senderContact(toSenderContact(orderData))
                .outWay(orderData.getOutWay())
                .warehouseId(new WarehouseId(orderData.getWhCd()))
                .customer(Customer.Builder.builder()
                        .id(new CustomerId(orderData.getCstCd()))
                        .name(orderData.getCustNm())
                        .build()
                )
                .orderNo(orderData.getOrdNo())
                .orderDate(orderData.getOrdDt())
                .actionType(actionType)
                .vehicleInfo(toVehicleInfo(orderData))
                .customerBrandedBox(orderData.getCustomerBrandedBox())
                .boxCategory(orderData.getBoxCategory())
                .ordDivVal(orderData.getOrdDivVal())
                .documentList(toDocumentList(request.getCoupangFileList()))
                .build();
    }

    public List<SalesOrder> soloChainSalesOrdersRequestToSalesOrderList(SoloChainSalesOrdersRequest request, ActionType actionType) {
        return request.getSalesOrders().stream().map(req -> soloChainSalesOrderRequestToSalesOrder(req, actionType)).toList();
    }

    public List<Result> salesOrdersToResult(List<SalesOrder> salesOrders) {
        return salesOrders.stream().map(this::salesOrderToResult).toList();
    }

    private Result salesOrderToResult(SalesOrder salesOrder) {
        return Result.builder()
                .salesOrder(salesOrder)
                .result("success")
                .build();
    }

    private List<Document> toDocumentList(List<CoupangFile> coupangFileList) {
        if (CollectionUtils.isEmpty(coupangFileList)) {
            return null;
        }
        return coupangFileList.stream().map(this::toDocument).toList();
    }

    private Document toDocument(CoupangFile coupangFile) {
        return Document.builder()
                .source(DocumentSource.COUPANG)
                .slipNo(coupangFile.getSlipNo())
                .fileType(coupangFile.getFileType())
                .originalFileName(coupangFile.getOrgFileNm())
                .fileName(coupangFile.getFileNm())
                .filePath(coupangFile.getFilePath())
                .fileExtension(coupangFile.getFileExt())
                .fileVolume(coupangFile.getFileVol())
                .build();
    }

    public SoloChainSalesOrderResponse salesOrderToSoloChainSalesOrderResponse(SalesOrder salesOrder) {
        return new SoloChainSalesOrderResponse(salesOrder.getId().getValue());
    }

    public SoloChainSalesOrdersResponse salesOrderToSoloChainSalesOrdersResponse(List<Result> results) {
        return new SoloChainSalesOrdersResponse(
                results.stream().map(this::toSalesOrderResult).toList()
        );
    }

    private SalesOrderResult toSalesOrderResult(Result result) {
        return SalesOrderResult.builder()
                .slipNo(result.salesOrder().getId().getValue())
                .ordNo(result.salesOrder().getOrderNo())
                .result(result.result())
                .build();
    }


    private Contact toSenderContact(OrderData orderData) {
        return Contact.builder()
                .name(orderData.getSendNm())
                .telephoneNo(orderData.getSendTelNo())
                .instruction(orderData.getNewRemark()) // instruction to center
                .packingInstruction(orderData.getPickingInstruction())
                .packingInstruction(orderData.getPackingInstruction())
                .shippingInstruction(orderData.getShippingInstruction())
                .build();
    }

    private Contact toShipToContact(OrderData orderData) {
        return Contact.builder()
                .name(orderData.getCustNm())
                .telephoneNo(orderData.getCustTelNo())
                .telephoneNoEtc(orderData.getCustTelNoEtc())
                .frontDoorPassword(orderData.getFrontDoorPwd())
                .address1(orderData.getCustAddr())
                .address2(orderData.getCustAddrDtl())
                .zipCode(orderData.getZipNo())
                .instruction(orderData.getShipReqTerm()) // 배송 요청사항
                .build();
    }

    private List<OrderLine> toOrderLineList(List<Goods> goodsList) {
        List<OrderLine> orderLineList = new ArrayList<>();

        for (Goods goods : goodsList) {
            orderLineList.add(toOrderLine(goods));
        }

        return orderLineList;
    }

    private boolean isGoodsOrBlankByGodDiv(String godDiv) {
        return StringUtils.isBlank(godDiv) || "god".equalsIgnoreCase(godDiv);
    }

    private OrderLine toOrderLine(Goods goods) {
        return OrderLine.Builder.builder()
                .id(new OrderLineId(goods.getItemSeq()))
                .itemSimple(toItem(goods))
                .quantityInfo(QuantityInfo.builder()
                        .godDiv(goods.getGodDiv())
                        .orderQuantity(isGoodsOrBlankByGodDiv(goods.getGodDiv()) ? goods.getOrdQty() : goods.getAddGodOrdQty())
                        .build()
                )
                .outWay(goods.getOutWay())
                .lotNo(goods.getLotNo())
                .expiryDate(goods.getDistTermDt())
                .condition(goods.getCondition())
                .build();
    }

    private ItemSimple toItem(Goods goods) {
        return ItemSimple.Builder.builder()
                .id(new ItemId(goods.getGodCd()))
                .type(goods.getGodType())
                .build();
    }

    private VehicleInfo toVehicleInfo(OrderData orderData) {
        return VehicleInfo.builder()
                .type(VehicleType.COURIER)
                .companyCode(orderData.getParcelCd())
                .trackingNo(orderData.getInvoiceNo())
                .shippingServiceLevelCode(orderData.getShippingServiceLevelCode())
                .shippingServiceLevelName(orderData.getShippingServiceLevelName())
                .build();
    }


}
