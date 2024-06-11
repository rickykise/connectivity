package ai.fassto.connectivity.externalservice.carrier.tms.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.port.output.external.api.carrier.ParcelCarrier;
import ai.fassto.connectivity.domain.parcel.application.port.output.repository.ParcelRepository;
import ai.fassto.connectivity.domain.parcel.core.valueobject.OutOrder;
import ai.fassto.connectivity.domain.salesorder.core.exception.SalesOrderNotFoundException;
import ai.fassto.connectivity.externalservice.carrier.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsFinalCallRequest;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsFinalCallResponse;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsPreCallRequest;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsPreCallResponse;
import ai.fassto.connectivity.externalservice.carrier.tms.mapper.TmsCarrierMapper;
import ai.fassto.connectivity.externalservice.carrier.tms.send.rest.TmsFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_PARCEL;


@Slf4j
@Component
@RequiredArgsConstructor
public class ParcelCarrierImpl implements ParcelCarrier {
    private final TmsCarrierMapper mapper;
    private final TmsFeignClient tmsFeignClient;
    private final ConnectivityPropertyService propertyService;
    private final ParcelRepository parcelRepository;

    @Value("${tms.api.pre-call}")
    private String tmsPreCallApiUrl;
    @Value("${tms.api.final-call}")
    private String tmsFinalCallApiUrl;


    @Override
    public TmsPreCallParcelResponse sendPreCall(TmsPreCallParcelRequest request) {
        TmsPreCallResponse response = null;

        try {
            URI uri = URI.create(propertyService.getTmsHostUrl());
            TmsPreCallRequest soloParams = mapper.tmsPreCallParcelRequestToTmsPreCallRequest(request);
            log.info("url: {}, soloParams: {}", uri + tmsPreCallApiUrl, toJson(soloParams));

            response = tmsFeignClient.createPreCall(uri, toHeaders(), soloParams);
            log.info("response: {}", toJson(response));
        } catch (FeignException exception) {
            log.info("error: {}", exception.getMessage());
            FeignExceptionHandler.tmsExternalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            FeignExceptionHandler.tmsExternalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_PARCEL, "API Call Result response is null")));
        }

        try {
            updateOutOrdParcelCdInvoiceNoWhenTmsPreCall(request, response);
        } catch (Exception exception) {
            log.error("PRE CALL out ord table 저장 중 에러 발생", exception);
        }


        return mapper.tmsPreCallResponseToTmsPreCallParcelResponse(response, request.getDeliveryCompanyCode());
    }

    public void updateOutOrdParcelCdInvoiceNoWhenTmsPreCall(TmsPreCallParcelRequest request, TmsPreCallResponse response) {
        //출고요청건 존재하는지 확인
        checkOutOrder(request);

        // 택배사코드, 송장번호 update (outOrd)
        parcelRepository.updateOutOrdParcelCdInvoiceNo(
                mapper.TmsPreCallRequestAndResponseToTmsPreCall(request, response)
        );
    }

    public void checkOutOrder(TmsPreCallParcelRequest request) {
        Optional<OutOrder> outOrderOptional = parcelRepository.findOneOutOrdOptional(
                mapper.TmsPreCallRequestToTmsPreCall(request)
        );

        if (outOrderOptional.isEmpty()) {
            throw new SalesOrderNotFoundException(new ErrorDetail("slipNo", request.getSlipNo()));
        }
    }

    @Override
    public TmsFinalCallParcelResponse sendFinalCall(TmsFinalCallParcelRequest request) {
        TmsFinalCallResponse response = null;

        try {
            URI uri = URI.create(propertyService.getTmsHostUrl());
            TmsFinalCallRequest soloParams = mapper.tmsFinalCallParcelRequestToTmsFinalCallRequest(request);
            log.info("url: {}, soloParams: {}", uri + tmsFinalCallApiUrl, toJson(soloParams));

            response = tmsFeignClient.createFinalCall(uri, toHeaders(), soloParams);
            log.info("response: {}", toJson(response));
        } catch (FeignException exception) {
            log.info("error: {}", exception.getMessage());
            FeignExceptionHandler.tmsExternalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            FeignExceptionHandler.tmsExternalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_PARCEL, "API Call Result response is null")));
        }

        return mapper.tmsFinalCallResponseToTmsFinalCallParcelResponse(response);
    }

}
