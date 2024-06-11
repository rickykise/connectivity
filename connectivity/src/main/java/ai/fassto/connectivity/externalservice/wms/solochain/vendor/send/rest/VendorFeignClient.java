package ai.fassto.connectivity.externalservice.wms.solochain.vendor.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.vendor.dto.VendorWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.dto.VendorWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-vendor", url ="${wms.api.host}")
public interface VendorFeignClient {

    @PostMapping(value = "${wms.api.vendor}")
    @ResponseBody
    VendorWmsSoloChainResponse updateVendor(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody VendorWmsSoloChainRequest request
    );
}
