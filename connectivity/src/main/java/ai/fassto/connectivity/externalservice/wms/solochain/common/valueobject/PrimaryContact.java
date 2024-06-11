package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record PrimaryContact(
        @JsonProperty("FirstName")
        String firstName_,

        @JsonProperty("LastName")
        String lastName_,

        @JsonProperty("Organization")
        String organization_,

        @JsonProperty("Position")
        String position_,

        @JsonProperty("Site")
        Site site_,

        @JsonProperty("Status")
        String status_
) {

    @Builder
    @JsonInclude(NON_NULL)
    public record Site(
            @JsonProperty("AccountNo")
            String accountNo_,

            @JsonProperty("Class")
            String className_,

            @JsonProperty("SiteType")
            SiteType siteType_
    ) {
    }
}