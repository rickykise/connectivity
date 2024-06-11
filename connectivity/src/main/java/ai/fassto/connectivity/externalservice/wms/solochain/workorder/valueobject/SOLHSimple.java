package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLHSimple(
        @JsonProperty("Lifecycle")
        String lifecycle_,// "Internal",

        @JsonProperty("OrderClass")
        String orderClass_,// "Transfer",

        @JsonProperty("OrderNo")
        String orderNo_// "DG-WO-20221216-13"
) {
}
