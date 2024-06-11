package ai.fassto.connectivity.externalservice.wms.solochain.vendor.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.vendor.application.port.output.external.api.wms.VendorWms;
import ai.fassto.connectivity.domain.vendor.core.entity.Vendor;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.dto.VendorWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.dto.VendorWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.mapper.VendorWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.send.rest.VendorFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Component
@RequiredArgsConstructor
@Slf4j
public class VendorWmsSoloChainImpl implements VendorWms {
    private final VendorWmsSoloChainMapper mapper;
    private final VendorFeignClient vendorFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${wms.api.vendor}")
    private String vendorApiUrl;


    @Override
    public Vendor create(Vendor vendor) {
        return callVendorApi(vendor);
    }

    @Override
    public Vendor update(Vendor vendor) {
        return callVendorApi(vendor);
    }

    private Vendor callVendorApi(Vendor vendor) {
        VendorWmsSoloChainResponse response = null;
        try {
            URI uri = URI.create(propertyService.getSolochainHostUrl(vendor.getWarehouseId().getValue()));
            VendorWmsSoloChainRequest soloParams = mapper.vendorToVendorWmsSoloChainRequest(vendor);
            log.info("[supCd: {}] url: {}, soloParams: {}", vendor.getId().getValue(), uri + vendorApiUrl, toJson(soloParams));

            response = vendorFeignClient.updateVendor(
                    uri, toHeaders(propertyService.getApiKey(vendor.getWarehouseId().getValue())), soloParams
            );
            log.info("[supCd: {}] response: {}", vendor.getId().getValue(), toJson(response));
        } catch (FeignException exception) {
            log.info("[supCd: {}] error: {}", vendor.getId().getValue(), toJson(exception.getMessage()));
            FeignExceptionHandler.externalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            log.info("[supCd: {}] error: response body empty", vendor.getId().getValue());
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(SoloChainConstant.DOMAIN_VENDOR, "API Call Result response is null")));
        } else if (!SoloChainConstant.isSuccessResponse(response.getResult())) {
            log.info("[supCd: {}] error: {}", vendor.getId().getValue(), toJson(response.getMessages()));
            FeignExceptionHandler.vendorExceptionHandler(response.getMessages());
        }

        return vendor;
    }
}
