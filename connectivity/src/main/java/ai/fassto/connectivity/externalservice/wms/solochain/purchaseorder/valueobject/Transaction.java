package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.valueobject;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record Transaction(
        @JsonProperty("SOLH")
        List<SOLH> solhs_
) {
}
