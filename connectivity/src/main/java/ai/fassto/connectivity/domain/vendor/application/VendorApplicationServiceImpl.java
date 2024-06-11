package ai.fassto.connectivity.domain.vendor.application;

import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorRequest;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse;
import ai.fassto.connectivity.domain.vendor.application.port.input.service.VendorApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorApplicationServiceImpl implements VendorApplicationService {
    private final VendorCreateRequestHandler vendorCreateRequestHandler;
    private final VendorUpdateRequestHandler vendorUpdateRequestHandler;

    @Override
    public SoloChainVendorResponse create(SoloChainVendorRequest request) {
        return vendorCreateRequestHandler.createVendor(request);
    }

    @Override
    public SoloChainVendorResponse update(SoloChainVendorRequest request) {
        return vendorUpdateRequestHandler.updateVendor(request);
    }
}
