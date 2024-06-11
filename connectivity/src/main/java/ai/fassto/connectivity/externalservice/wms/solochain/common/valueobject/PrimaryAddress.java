package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Builder
@JsonInclude(NON_NULL)
public record PrimaryAddress(
        @JsonProperty("Address1")
        String address1_,

        @JsonProperty("Address2")
        String address2_,

        @JsonProperty("Address3")
        String address3_,

        @JsonProperty("Address4")
        String address4_,

        @JsonProperty("City")
        String city_,

        @JsonProperty("Province")
        String province_,

        @JsonProperty("PostalCode")
        String postalCode_,

        @JsonProperty("Country")
        String country_
) {
}
