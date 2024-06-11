package ai.fassto.connectivity.externalservice.wms.solochain.vehicle.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.dto.VehicleAllocationInfoWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.dto.VehicleAllocationInfoWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-vehicle", url ="${wms.api.host}")
public interface VehicleFeignClient {

    @PostMapping(value = "${wms.api.vehicle-confirmation}")
    @ResponseBody
    VehicleAllocationInfoWmsSoloChainResponse updateVehicleAllocationInfo(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody VehicleAllocationInfoWmsSoloChainRequest request
    );
}
