package ai.fassto.connectivity.domain.vendor.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.BusinessRegistration;
import ai.fassto.connectivity.domain.common.valueobject.id.VendorId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorRequest;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse;
import ai.fassto.connectivity.domain.vendor.core.entity.Vendor;
import org.springframework.stereotype.Component;

import static ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType.isY;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EStatus.ACTIVE;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EStatus.INACTIVE;

@Component
public class VendorDataMapper {
    public Vendor soloChainVendorRequestToVendor(SoloChainVendorRequest request, ActionType actionType) {
        return Vendor.Builder.builder()
                .vendorId(new VendorId(request.getSupCd()))
                .businessRegistration(toBusinessRegistration(request))
                .status(isY(request.getUseYn()) ? ACTIVE : INACTIVE)
                .actionType(actionType)
                .warehouseId(new WarehouseId(request.getWhCd()))
                .build();
    }

    public SoloChainVendorResponse vendorToSoloChainVendorResponse(Vendor vendor) {
        return SoloChainVendorResponse.builder()
                .supCd(vendor.getId().getValue())
                .supNm(vendor.getBusinessRegistration().getCompanyName()).build();
    }

    private BusinessRegistration toBusinessRegistration(SoloChainVendorRequest request) {
        return BusinessRegistration.builder()
                .companyName(request.getSupNm())
                .zipCode(request.getZipNo())
                .address1(request.getAddr1())
                .address2(request.getAddr2())
                .phoneNo(request.getTelNo())
                .build();
    }
}
