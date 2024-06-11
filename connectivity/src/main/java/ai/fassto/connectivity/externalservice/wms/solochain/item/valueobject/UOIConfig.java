package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record UOIConfig(
        @JsonProperty("Name") // : "EA",
        String name_,

        @JsonProperty("Status") // : "Active",
        String status_,

        @JsonProperty("BaseUOIQuantity") // : 1,
        Integer baseUOIQuantity_,

        @JsonProperty("Width") // : 0,
        BigDecimal width_,

        @JsonProperty("Depth") // : 0,
        BigDecimal depth_,

        @JsonProperty("Height") // : 0,
        BigDecimal height_,

        @JsonProperty("DimensionUOM") // : {
        DimensionUOM dimensionUOM_,

        @JsonProperty("Weight") // : 0,
        BigDecimal weight_,

        @JsonProperty("WeightUOM")
        WeightUOM weightUOM_,

        @JsonProperty("GTIN")
        GTIN gtin_
) {

}
