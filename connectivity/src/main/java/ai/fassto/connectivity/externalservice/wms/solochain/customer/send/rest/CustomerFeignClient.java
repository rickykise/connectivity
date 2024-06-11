package ai.fassto.connectivity.externalservice.wms.solochain.customer.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.customer.dto.CustomerWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.dto.CustomerWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-customer", url ="${wms.api.host}")
public interface CustomerFeignClient {

    @PostMapping(value = "${wms.api.customer}")
    @ResponseBody
    CustomerWmsSoloChainResponse updateCustomer(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody CustomerWmsSoloChainRequest request
    );
}