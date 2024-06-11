package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.dto.SalesOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.dto.SalesOrderWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-sales-order", url ="${wms.api.host}")
public interface SalesOrderFeignClient {

    @PostMapping(value = "${wms.api.sales-order}")
    @ResponseBody
    SalesOrderWmsSoloChainResponse updateSalesOrder(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody SalesOrderWmsSoloChainRequest request
    );
}
