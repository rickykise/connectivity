package ai.fassto.connectivity.externalservice.oms.smartstore.send;

import ai.fassto.connectivity.externalservice.oms.smartstore.dto.SmartStoreRegisterInvoiceRequest;
import ai.fassto.connectivity.externalservice.oms.smartstore.dto.SmartStoreRegisterInvoiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "smart-store-oms", url = "${wms.api.host}")
public interface SmartStoreFeignClient {

    @PostMapping(value = "${smartstore.api.register-invoice}")
    @ResponseBody
    SmartStoreRegisterInvoiceResponse registerInvoice(
            URI baseUrl,
            @RequestHeader Map<String, String> headers,
            @RequestBody SmartStoreRegisterInvoiceRequest request
    );
}