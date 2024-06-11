package ai.fassto.connectivity.externalservice.wms.solochain.customer.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.customer.application.port.output.external.api.wms.CustomerWms;
import ai.fassto.connectivity.domain.customer.core.entity.Customer;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.dto.CustomerWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.dto.CustomerWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.mapper.CustomerWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.send.rest.CustomerFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_CUSTOMER;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.isSuccessResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerWmsSoloChainImpl implements CustomerWms {

    private final CustomerWmsSoloChainMapper mapper;
    private final CustomerFeignClient customerFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${wms.api.customer}")
    private String customerApiUrl;


    @Override
    public Customer create(Customer customer) {
        return callCustomerApi(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return callCustomerApi(customer);
    }

    private Customer callCustomerApi(Customer customer) {
        CustomerWmsSoloChainResponse response = null;

        try {
            URI uri = URI.create(propertyService.getSolochainHostUrl(customer.getWarehouseId().getValue()));
            CustomerWmsSoloChainRequest soloParams = mapper.customerToCustomerWmsSoloChainRequest(customer);
            log.info("[cstCd: {}] url: {}, soloParams: {}", customer.getId().getValue(), uri + customerApiUrl, toJson(soloParams));

            response = customerFeignClient.updateCustomer(
                    uri, toHeaders(propertyService.getApiKey(customer.getWarehouseId().getValue())), soloParams
            );
            log.info("[cstCd: {}] response: {}", customer.getId().getValue(), toJson(response));
        } catch (FeignException exception) {
            log.info("[cstCd: {}] error: {}", customer.getId().getValue(), toJson(response));
            FeignExceptionHandler.externalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            log.info("[cstCd: {}] error: response body empty", customer.getId().getValue());
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_CUSTOMER, "API Call Result response is null")));
        } else if (!isSuccessResponse(response.getResult())) {
            log.info("[cstCd: {}] error: {}", customer.getId().getValue(), response.getMessages());
            FeignExceptionHandler.customerExceptionHandler(response.getMessages());
        }

        return customer;
    }


}
