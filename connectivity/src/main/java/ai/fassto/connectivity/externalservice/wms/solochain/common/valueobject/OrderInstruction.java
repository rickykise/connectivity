package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record OrderInstruction( // item inspection
    @JsonProperty("Type")
    String type_,

    @JsonProperty("Instruction")
    String instruction_ // 100% - overall inspection, less than 100%  - inspection to some samples
) {
}