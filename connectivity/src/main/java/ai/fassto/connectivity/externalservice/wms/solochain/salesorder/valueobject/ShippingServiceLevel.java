package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ShippingServiceLevel(
        @JsonProperty("Code")
        String code_, // ": "24시",
        @JsonProperty("Name")
        String name_, // ": "24시간배달",
        @JsonProperty("Carrier")
        Carrier carrier_
) {
}
