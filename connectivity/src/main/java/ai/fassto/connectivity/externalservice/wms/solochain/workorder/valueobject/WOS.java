package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.MaterialMaster;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.OrderInstruction;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.UOI;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record WOS(
        @JsonProperty("Sequence")
        String sequence_,

        @JsonProperty("WOH")
        WOHSimple wOH_,

        @JsonProperty("Status")
        String status_,

        @JsonProperty("Quantity")
        Integer quantity_,

        @JsonProperty("RequiredDate")
        String requiredDate_,

        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,

        @JsonProperty("UOI")
        UOI uoi_,

        @JsonProperty("OrderInstruction")
        List<OrderInstruction> orderInstruction_
) {
}
