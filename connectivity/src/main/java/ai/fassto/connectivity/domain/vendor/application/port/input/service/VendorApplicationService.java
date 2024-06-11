package ai.fassto.connectivity.domain.vendor.application.port.input.service;

import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorRequest;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse;

public interface VendorApplicationService {
    SoloChainVendorResponse create(SoloChainVendorRequest request);

    SoloChainVendorResponse update(SoloChainVendorRequest request);
}
