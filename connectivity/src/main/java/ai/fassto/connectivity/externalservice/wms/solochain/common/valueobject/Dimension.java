package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record Dimension(
        @JsonProperty("@attr")
        Attr attr_,
        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,
        @JsonProperty("Value")
        String value_ //: "lot no: 123456"
) {
}
