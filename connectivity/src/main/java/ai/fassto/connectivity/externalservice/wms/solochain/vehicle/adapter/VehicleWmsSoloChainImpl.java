package ai.fassto.connectivity.externalservice.wms.solochain.vehicle.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.VehicleAllocationInfo;
import ai.fassto.connectivity.domain.parcel.application.port.output.external.api.wms.VehicleWms;
import ai.fassto.connectivity.domain.parcel.application.valueobject.AllocationInfo;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.dto.VehicleAllocationInfoWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.dto.VehicleAllocationInfoWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.mapper.VehicleWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.send.rest.VehicleFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_PARCEL;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.isSuccessResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleWmsSoloChainImpl implements VehicleWms {

    private final VehicleWmsSoloChainMapper mapper;
    private final VehicleFeignClient vehicleFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${wms.api.vehicle-confirmation}")
    private String vehicleApiUrl;

    @Override
    public SoloChainVehicleAllocationInfoResponse updateVehicleAllocationInfo(SoloChainVehicleAllocationInfoRequest request) {
        VehicleAllocationInfoWmsSoloChainResponse response = null;
        String wareHouseCode = request.getVehicleAllocationInfoList().get(0).getWareHouseCode();

        try {
            URI uri = URI.create(propertyService.getSolochainHostUrl(wareHouseCode));
            VehicleAllocationInfoWmsSoloChainRequest soloParams = mapper.vehicleAllocationInfoUpdateRequestToVehicleAllocationInfoWmsSoloChainRequest(request);
            log.info("url: {}, soloParams: {}", uri + vehicleApiUrl, toJson(soloParams));

            response = vehicleFeignClient.updateVehicleAllocationInfo(
                    uri, toHeaders(propertyService.getApiKey(wareHouseCode)), soloParams
            );
            log.info("response: {}", toJson(response));
        } catch (FeignException exception) {
            log.info("error: {}", exception.getMessage());
            FeignExceptionHandler.externalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            log.info("error: response body empty");
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_PARCEL, "API Call Result response is null")));
        } else if (!isSuccessResponse(response.getResult())) {
            log.info("error: {}", response.getMessages());
            FeignExceptionHandler.VehicleParcelExceptionHandler(response.getMessages());
        }

        return new SoloChainVehicleAllocationInfoResponse(toAllocationInfos(request.getVehicleAllocationInfoList()));
    }

    private List<AllocationInfo> toAllocationInfos(List<VehicleAllocationInfo> list) {
        return list.stream().map(this::toAllocationInfo).toList();
    }

    private AllocationInfo toAllocationInfo(VehicleAllocationInfo info) {
        return AllocationInfo.builder()
                .warehouseCode(info.getWareHouseCode())
                .code(info.getCode())
                .arrangeNo(info.getArrangeNo())
                .build();
    }
}