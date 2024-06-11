package ai.fassto.connectivity.domain.customer.application.port.input.service;


import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerRequest;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerResponse;

public interface CustomerApplicationService {

    SoloChainCustomerResponse create(SoloChainCustomerRequest request);
    SoloChainCustomerResponse update(SoloChainCustomerRequest request);
}
