package ai.fassto.connectivity.externalservice.carrier.tms.send.rest;

import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsFinalCallRequest;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsFinalCallResponse;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsPreCallRequest;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsPreCallResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "carrier-tms", url ="${wms.api.host}")
public interface TmsFeignClient {

    @PostMapping(value = "${tms.api.pre-call}")
    @ResponseBody
    TmsPreCallResponse createPreCall(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody TmsPreCallRequest request
    );

    @PostMapping(value = "${tms.api.final-call}")
    @ResponseBody
    TmsFinalCallResponse createFinalCall(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody TmsFinalCallRequest request
    );
}