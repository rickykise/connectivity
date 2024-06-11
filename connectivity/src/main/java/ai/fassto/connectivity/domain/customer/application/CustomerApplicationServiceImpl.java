package ai.fassto.connectivity.domain.customer.application;

import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerRequest;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerResponse;
import ai.fassto.connectivity.domain.customer.application.port.input.service.CustomerApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerApplicationServiceImpl implements CustomerApplicationService {
    private final CustomerCreateRequestHandler createRequestHandler;
    private final CustomerUpdateRequestHandler updateRequestHandler;

    @Override
    public SoloChainCustomerResponse create(SoloChainCustomerRequest request) {
        return createRequestHandler.createCustomer(request);
    }

    @Override
    public SoloChainCustomerResponse update(SoloChainCustomerRequest request) {
        return updateRequestHandler.updateCustomer(request);
    }
}
