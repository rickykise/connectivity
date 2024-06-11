package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record WOSSimple(
        @JsonProperty("Sequence")
        Integer sequence_,

        @JsonProperty("WOH")
        WOHSimple woh_
) {
}
