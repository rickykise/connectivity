package ai.fassto.connectivity.externalservice.wms.solochain.customer.mapper;

import ai.fassto.connectivity.domain.common.valueobject.BusinessRegistration;
import ai.fassto.connectivity.domain.common.valueobject.EmployeeInCharge;
import ai.fassto.connectivity.domain.customer.core.entity.Customer;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Language;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.PrimaryAddress;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.PrimaryContact;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.SiteType;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.dto.CustomerWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.customer.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.EEmployeeType.MASTER;
import static ai.fassto.connectivity.domain.common.valueobject.enums.ELanguageCode.KOREAN;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EStatus.ACTIVE;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.*;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ESiteType.CUSTOMER;

@Component
public class CustomerWmsSoloChainMapper {
    public CustomerWmsSoloChainRequest customerToCustomerWmsSoloChainRequest(Customer customer) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(
                Site.builder()
                        .accountNo_(customer.getId().getValue())
                        .name_(customer.getName())
                        .siteType_(new SiteType(CUSTOMER.getSolochainName()))
                        .language_(new Language(KOREAN.getCode()))
                        .status_(customer.getStatus().name())
                        .primaryAddress_(toPrimaryAddress(customer.getBusinessRegistration()))
                        .phoneNo_(customer.getBusinessRegistration().getPhoneNo())
                        .primaryContact_(toPrimaryContact(customer))
                        .customerRelation_(CustomerRelation.builder()
                                .supplier_(Supplier.builder().attr_(new Attr(SOLOCHAIN_APPLICATION)).accountNo_(FASSTO_APPLICATION).build())
                                .customer_(new ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Customer(
                                        customer.getId().getValue(),
                                        new SiteType(CUSTOMER.getSolochainName())
                                )).build())
                        .build()
        ));

        return new CustomerWmsSoloChainRequest(transactions);
    }

    private PrimaryContact toPrimaryContact(Customer customer) {
        EmployeeInCharge employee = customer.getEmployeeMap().get(MASTER.getNo());

        return PrimaryContact.builder()
                .firstName_("-")
                .lastName_(employee.getName())
                .position_(employee.getPosition())
                .site_(PrimaryContact.Site.builder()
                        .accountNo_(customer.getId().getValue())
                        .className_(SITE)
                        .siteType_(new SiteType(CUSTOMER.getSolochainName()))
                        .build()
                )
                .status_(ACTIVE.name())
                .build();
    }

    private PrimaryAddress toPrimaryAddress(BusinessRegistration businessRegistration) {
        return PrimaryAddress.builder()
                .address1_(businessRegistration.getAddress1())
                .address2_(businessRegistration.getAddress2())
                .postalCode_(businessRegistration.getZipCode())
                .build();
    }
}
