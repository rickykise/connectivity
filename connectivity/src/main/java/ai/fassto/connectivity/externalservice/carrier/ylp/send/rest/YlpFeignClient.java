package ai.fassto.connectivity.externalservice.carrier.ylp.send.rest;

import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationRequest;
import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "fss-ylp-batch", url ="${wms.api.host}")
public interface YlpFeignClient {

    @PostMapping("${ylp.api.ylp-insert}")
    VehicleAllocationResponse createVehicleAllocate(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody VehicleAllocationRequest request
    );

}
