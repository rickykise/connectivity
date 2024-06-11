package ai.fassto.connectivity.domain.customer.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerRequest;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerResponse;
import ai.fassto.connectivity.domain.customer.application.mapper.CustomerDataMapper;
import ai.fassto.connectivity.domain.customer.application.port.output.external.api.wms.CustomerWms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerUpdateRequestHandler {
    private final CustomerDataMapper customerDataMapper;
    private final CustomerWms customerWms;

    public SoloChainCustomerResponse updateCustomer(SoloChainCustomerRequest request) {
        return customerDataMapper.customerToSoloChainCustomerResponse(
                customerWms.update(
                        customerDataMapper.soloChainCustomerRequestToCustomer(request, ActionType.UPDATE)
                )
        );
    }
}
