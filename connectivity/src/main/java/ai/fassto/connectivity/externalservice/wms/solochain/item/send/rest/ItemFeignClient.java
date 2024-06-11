package ai.fassto.connectivity.externalservice.wms.solochain.item.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.ItemWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.ItemWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-item", url ="${wms.api.host}")
public interface ItemFeignClient {

    @PostMapping(value = "${wms.api.item}")
    @ResponseBody
    ItemWmsSoloChainResponse updateItem(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody ItemWmsSoloChainRequest request
    );

}
