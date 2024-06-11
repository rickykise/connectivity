package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record InternalRequest(
        @JsonProperty("ReferenceNo")
        String referenceNo_,

        @JsonProperty("WorkCenter")
        WorkCenter workCenter_,

        @JsonProperty("SOLH")
        SOLHSimple solh_
) {
}
