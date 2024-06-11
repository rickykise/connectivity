package ai.fassto.connectivity.domain.vendor.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorRequest;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse;
import ai.fassto.connectivity.domain.vendor.application.mapper.VendorDataMapper;
import ai.fassto.connectivity.domain.vendor.application.port.output.external.api.wms.VendorWms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendorUpdateRequestHandler {
    private final VendorDataMapper vendorDataMapper;
    private final VendorWms vendorWms;

    public SoloChainVendorResponse updateVendor(SoloChainVendorRequest request) {
        return vendorDataMapper.vendorToSoloChainVendorResponse(
                vendorWms.update(
                        vendorDataMapper.soloChainVendorRequestToVendor(request, ActionType.UPDATE)
                )
        );
    }
}
