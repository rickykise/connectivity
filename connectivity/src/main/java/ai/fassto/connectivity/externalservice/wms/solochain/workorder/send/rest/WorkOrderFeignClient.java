package ai.fassto.connectivity.externalservice.wms.solochain.workorder.send.rest;

import ai.fassto.connectivity.externalservice.wms.solochain.workorder.dto.WorkOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.dto.WorkOrderWmsSoloChainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "solochain-work-order", url ="${wms.api.host}")
public interface WorkOrderFeignClient {

    @PostMapping(value = "${wms.api.work-order}")
    @ResponseBody
    WorkOrderWmsSoloChainResponse updateWorkOrder(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody WorkOrderWmsSoloChainRequest request
    );

}
