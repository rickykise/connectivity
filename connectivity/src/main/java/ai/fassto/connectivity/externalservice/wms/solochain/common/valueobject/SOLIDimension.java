package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLIDimension(
        @JsonProperty("Dimension")
        Dimension dimension_,
        @JsonProperty("Quantity")
        String quantity_,
        @JsonProperty("UOI")
        UOI uoi_,
        @JsonProperty("Mode")
        String mode_ // "Hard"
) {
}
