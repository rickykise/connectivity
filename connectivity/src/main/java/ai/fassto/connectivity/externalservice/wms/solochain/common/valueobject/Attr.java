package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record Attr(
        @JsonProperty("Action")
        String action_,

        @JsonProperty("Parameter")
        String parameter_,

        @JsonProperty("Index")
        String index_,

        @JsonProperty("Class")
        String className_
) {
}
