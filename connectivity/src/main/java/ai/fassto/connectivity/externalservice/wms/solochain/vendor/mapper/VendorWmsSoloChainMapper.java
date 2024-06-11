package ai.fassto.connectivity.externalservice.wms.solochain.vendor.mapper;

import ai.fassto.connectivity.domain.common.valueobject.BusinessRegistration;
import ai.fassto.connectivity.domain.vendor.core.entity.Vendor;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Language;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.PrimaryAddress;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.SiteType;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.dto.VendorWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vendor.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.ELanguageCode.KOREAN;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.FASSTO_APPLICATION;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.SOLOCHAIN_APPLICATION;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ESiteType.VENDOR;

@Component
public class VendorWmsSoloChainMapper {
    public VendorWmsSoloChainRequest vendorToVendorWmsSoloChainRequest(Vendor vendor) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Site.builder()
                .accountNo_(vendor.getId().getValue())
                .name_(vendor.getBusinessRegistration().getCompanyName())
                .siteType_(new SiteType(VENDOR.getSolochainName()))
                .language_(new Language(KOREAN.getCode()))
                .status_(vendor.getStatus().name())
                .primaryAddress_(toPrimaryAddress(vendor.getBusinessRegistration()))
                .phoneNo_(vendor.getBusinessRegistration().getPhoneNo())
                .vendorRelation_(VendorRelation.builder()
                        .receiver_(Receiver.builder().attr_(new Attr(SOLOCHAIN_APPLICATION)).accountNo_(FASSTO_APPLICATION).build())
                        .vendor_(new ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Vendor(
                                vendor.getId().getValue(),
                                new SiteType(VENDOR.getSolochainName())
                        )).build()
                )
                .build()
        ));

        return new VendorWmsSoloChainRequest(transactions);
    }

    private PrimaryAddress toPrimaryAddress(BusinessRegistration businessRegistration) {
        return PrimaryAddress.builder()
                .address1_(businessRegistration.getAddress1())
                .address2_(businessRegistration.getAddress2())
                .postalCode_(businessRegistration.getZipCode())
                .build();
    }
}
