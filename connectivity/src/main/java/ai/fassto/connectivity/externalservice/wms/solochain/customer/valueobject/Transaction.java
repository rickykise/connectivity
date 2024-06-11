package ai.fassto.connectivity.externalservice.wms.solochain.customer.valueobject;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record Transaction(
        @JsonProperty("Site")
        Site site_
) {
}
