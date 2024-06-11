package ai.fassto.connectivity.externalservice.carrier.ylp.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse;
import ai.fassto.connectivity.domain.parcel.application.port.output.external.api.carrier.VehicleCarrier;
import ai.fassto.connectivity.domain.parcel.core.exception.YlpParcelFailedException;
import ai.fassto.connectivity.externalservice.carrier.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationRequest;
import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationResponse;
import ai.fassto.connectivity.externalservice.carrier.ylp.mapper.YlpCarrierMapper;
import ai.fassto.connectivity.externalservice.carrier.ylp.send.rest.YlpFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_PARCEL;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleCarrierImpl implements VehicleCarrier {

    private final YlpCarrierMapper mapper;
    private final YlpFeignClient ylpFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${ylp.api.ylp-insert}")
    private String ylpApiUrl;

    @Override
    public VehicleAllocationYlpResponse sendVehicleAllocate(VehicleAllocationYlpRequest request) {
        VehicleAllocationResponse response = null;

        try {
            URI uri = URI.create(propertyService.getYlpHostUrl());
            VehicleAllocationRequest soloParams = mapper.vehicleAllocationYlpRequestToVehicleAllocationRequest(request);
            log.info("url: {}, soloParams: {}", uri + ylpApiUrl, toJson(soloParams));

            response = ylpFeignClient.createVehicleAllocate(uri, toHeaders(), soloParams);
            log.info("response: {}", toJson(response));
        } catch (FeignException exception) {
            log.info("error: {}", exception.getMessage());
            FeignExceptionHandler.ylpExternalApiCallExceptionHandler(exception);
        }

        if (response == null) {
            FeignExceptionHandler.tmsExternalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_PARCEL, "API Call Result response is null")));

        } else if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
            throw new YlpParcelFailedException(new ErrorDetail("code", response.getCode()));
        }

        return mapper.vehicleAllocationResponseToVehicleAllocationYlpResponse(response);
    }
}
