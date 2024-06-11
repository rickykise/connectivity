package ai.fassto.connectivity.externalservice.wms.solochain.customer.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Language;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.PrimaryAddress;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.PrimaryContact;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.SiteType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record Site(
        @JsonProperty("AccountNo")
        String accountNo_,

        @JsonProperty("Name")
        String name_,

        @JsonProperty("SiteType")
        SiteType siteType_,

        @JsonProperty("Language")
        Language language_,

        @JsonProperty("Status")
        String status_,

        @JsonProperty("PrimaryAddress")
        PrimaryAddress primaryAddress_,

        @JsonProperty("Email")
        String email_,

        @JsonProperty("PhoneNo")
        String phoneNo_,

        @JsonProperty("WebSite")
        String webSite_,

        @JsonProperty("PrimaryContact")
        PrimaryContact primaryContact_,

        @JsonProperty("CustomerRelation")
        CustomerRelation customerRelation_
) {
}