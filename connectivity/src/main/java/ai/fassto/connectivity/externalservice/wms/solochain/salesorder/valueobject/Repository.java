package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Repository(
        @JsonProperty("Name")
        String name
) {

}
