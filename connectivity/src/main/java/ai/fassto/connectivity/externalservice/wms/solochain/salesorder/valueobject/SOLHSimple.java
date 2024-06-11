package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLHSimple(
        @JsonProperty("Lifecycle")
        String lifecycle_, // "Outbound",
        @JsonProperty("OrderClass")
        String orderClass_, // "Sales",
        @JsonProperty("OrderNo")
        String orderNo_ // "SO-DG-JSON1",
) {
}
