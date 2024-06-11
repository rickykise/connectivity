package ai.fassto.connectivity.externalservice.oms.smartstore.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.oms.SmartStoreOms;
import ai.fassto.connectivity.externalservice.carrier.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.oms.smartstore.dto.SmartStoreRegisterInvoiceRequest;
import ai.fassto.connectivity.externalservice.oms.smartstore.dto.SmartStoreRegisterInvoiceResponse;
import ai.fassto.connectivity.externalservice.oms.smartstore.mapper.SmartStoreOmsMapper;
import ai.fassto.connectivity.externalservice.oms.smartstore.send.SmartStoreFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_SALES_ORDER;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmartStoreOmsImpl implements SmartStoreOms {
    private final ConnectivityPropertyService propertyService;
    private final SmartStoreOmsMapper mapper;
    private final SmartStoreFeignClient smartStoreFeignClient;

    @Value("${smartstore.api.register-invoice}")
    private String omsSmartStoreApiUrl;

    @Override
    public SmartStoreRegisterInvoiceResponse registerInvoice(String slipNo, String orderNo, String customerCode, String invoice, String deliveryCompanyCode) {
        SmartStoreRegisterInvoiceResponse response = null;

        try {
            URI uri = URI.create(propertyService.getOmsSmartStoreHostUrl());
            SmartStoreRegisterInvoiceRequest omsParams = mapper.toSmartStoreRegisterInvoiceRequest(slipNo, orderNo, customerCode, invoice, deliveryCompanyCode);
            log.info("url: {}, omsParams: {}", uri + omsSmartStoreApiUrl, toJson(omsParams));

            response = smartStoreFeignClient.registerInvoice(uri, toHeaders(), omsParams);
            log.info("response: {}", toJson(response));
        } catch (FeignException exception) {
            log.info("error: {}", exception.getMessage());
            FeignExceptionHandler.omsExternalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            FeignExceptionHandler.tmsExternalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_SALES_ORDER, "OMS Smart Store API Call Result response is null")));
        }

        return response;
    }
}
