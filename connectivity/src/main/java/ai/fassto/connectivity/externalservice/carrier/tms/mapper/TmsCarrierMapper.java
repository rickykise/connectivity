package ai.fassto.connectivity.externalservice.carrier.tms.mapper;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.valueobject.Package;
import ai.fassto.connectivity.domain.parcel.application.valueobject.*;
import ai.fassto.connectivity.domain.parcel.core.entitiy.TmsPreCall;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.CourierType;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsFinalCallRequest;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsFinalCallResponse;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsPreCallRequest;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsPreCallResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TmsCarrierMapper {
    private final ModelMapper mapper;

    public TmsPreCallRequest tmsPreCallParcelRequestToTmsPreCallRequest(TmsPreCallParcelRequest request) {
        TmsPreCallRequest tmsPreCallRequest = mapper.map(request, TmsPreCallRequest.class);
        tmsPreCallRequest.setPickDate(request.getPickDate() == null ? null : request.getPickDate().toLocalDate());
        return tmsPreCallRequest;
    }

    public TmsPreCallParcelResponse tmsPreCallResponseToTmsPreCallParcelResponse(TmsPreCallResponse response, String deliveryCompanyCode) {
        return TmsPreCallParcelResponse.builder()
                .solh_(SOLH.builder()
                        .orderNo_(response.getSlipNo())
                        .trackingNumber_(response.getInvoiceNo())
                        .build()
                )
                .deliveryCompanyCode(deliveryCompanyCode)
                .build();
    }

    public TmsFinalCallRequest tmsFinalCallParcelRequestToTmsFinalCallRequest(TmsFinalCallParcelRequest request) {
        TmsFinalCallRequest tmsFinalCallRequest = mapper.map(request, TmsFinalCallRequest.class);
        tmsFinalCallRequest.setPickDate(request.getPickDate() == null ? null : request.getPickDate().toLocalDate());
        return tmsFinalCallRequest;
    }

    public TmsFinalCallParcelResponse tmsFinalCallResponseToTmsFinalCallParcelResponse(TmsFinalCallResponse response) {
        return TmsFinalCallParcelResponse.builder()
                .shipResponse_(
                        new ShipResponse(
                                ShipResult.builder()
                                        .warehouseCode(response.getWarehouseCode())
                                        .customerCode(response.getCustomerCode())
                                        .customerName(response.getCustomerName())
                                        .deliveryCompanyCode(response.getDeliveryCompanyCode())
                                        .deliveryType(response.getDeliveryType())
                                        .changingShippingOption(response.getChangingShippingOption())
                                        .orderNo(response.getOrderNo())
                                        .pickDate(LocalDateTime.of(response.getPickDate(), LocalTime.of(0, 0)))
                                        .senderName(response.getSenderName())
                                        .senderPhone(response.getSenderPhone())
                                        .senderPhoneEtc(response.getSenderPhoneEtc())
                                        .senderZipCode(response.getSenderZipCode())
                                        .senderAddress(response.getSenderAddress())
                                        .senderAddressDetail(response.getSenderAddressDetail())
                                        .receiverName(response.getReceiverName())
                                        .receiverPhone(response.getReceiverPhone())
                                        .receiverPhoneEtc(response.getReceiverPhoneEtc())
                                        .receiverZipCode(response.getReceiverZipCode())
                                        .receiverAddress(response.getReceiverAddress())
                                        .receiverAddressDetail(response.getReceiverAddressDetail())
                                        .shipRequestMemo(response.getShipRequestMemo())
                                        .entrancePassword(response.getEntrancePassword())
                                        .Packages(toPackages(response))
                                        .build()
                        )
                ).build();
    }

    private List<Package> toPackages(TmsFinalCallResponse response) {
        if (CollectionUtils.isEmpty(response.getPackages())) {
            return new ArrayList<>();
        }

        return response.getPackages().stream().map(this::toPackage).toList();
    }

    private Package toPackage(ai.fassto.connectivity.externalservice.carrier.tms.dto.Package pack) {
        return Package.builder()
                .boxID(pack.getBoxID())
                .invoiceNumber(pack.getInvoiceNumber())
                .basicFare(pack.getBasicFare())
                .dealFare(pack.getDealFare())
                .airFare(pack.getAirFare())
                .shipFare(pack.getShipFare())
                .boxType(pack.getBoxType())
                .boxWidth(pack.getBoxWidth())
                .boxHeight(pack.getBoxHeight())
                .boxDepth(pack.getBoxDepth())
                .boxWeight(pack.getBoxWeight())
                .printedDocuments(
                        new PrintedDocument(
                                ProShipPrintOutputItem.builder()
                                        .documentName(pack.getPrintDocument() == null ? null : pack.getPrintDocument().getDocumentName())
                                        .encodedContents(pack.getPrintDocument() == null ? null : pack.getPrintDocument().getEncodedContents())
                                        .build()
                        )
                )
                .build();
    }

    public TmsPreCall TmsPreCallRequestAndResponseToTmsPreCall(TmsPreCallParcelRequest request, TmsPreCallResponse response) {
        return TmsPreCall.builder()
                .outOrdSlipNo(request.getSlipNo())
                .whCd(request.getWarehouseCode())
                .parcelCd(StringUtils.isBlank(request.getDeliveryCompanyCode()) ? null : CourierType.getErpCodeBy(request.getDeliveryCompanyCode()))
                .invoiceNo(StringUtils.isBlank(response.getInvoiceNo()) ? null : response.getInvoiceNo())
                .build();
    }

    public TmsPreCall TmsPreCallRequestToTmsPreCall(TmsPreCallParcelRequest request) {
        return TmsPreCall.builder()
                .outOrdSlipNo(request.getSlipNo())
                .whCd(request.getWarehouseCode())
                .cstCd(request.getCustomerCode())
                .build();
    }
}
