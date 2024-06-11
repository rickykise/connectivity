package ai.fassto.connectivity.externalservice.partnerApi.send.rest;

import ai.fassto.connectivity.externalservice.partnerApi.dto.PartnerApiPromotionRequest;
import ai.fassto.connectivity.externalservice.partnerApi.dto.PartnerApiPromotionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "partner-api", url = "${wms.api.host}")
public interface PartnerApiFeignClient {

    @PostMapping(value = "${partner-api.api.create-promotion}")
    @ResponseBody
    PartnerApiPromotionResponse createPormotion(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody PartnerApiPromotionRequest request
    );

}