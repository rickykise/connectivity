package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record OrderType(
        @JsonProperty("Name")
        String name_
) {
}