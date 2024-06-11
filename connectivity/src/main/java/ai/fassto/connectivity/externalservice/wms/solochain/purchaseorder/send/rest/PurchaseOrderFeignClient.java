package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.dto.PurchaseOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.dto.PurchaseOrderWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-purchase-order", url ="${wms.api.host}")
public interface PurchaseOrderFeignClient {

    @PostMapping(value = "${wms.api.purchase-order}")
    @ResponseBody
    PurchaseOrderWmsSoloChainResponse updatePurchaseOrder(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody PurchaseOrderWmsSoloChainRequest request
    );

    @PostMapping(value = "${wms.api.return-order}")
    @ResponseBody
    PurchaseOrderWmsSoloChainResponse updateReturnOrder(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody PurchaseOrderWmsSoloChainRequest request
    );
}
