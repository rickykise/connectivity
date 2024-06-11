package ai.fassto.connectivity.domain.item.application.dto.erp.update.volume;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ErpItemVolumeRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "itemCode (아이템 코드)", example = "01328TESTGOD1")
    private String itemCode;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "width", example = "100")
    private Float width;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "height", example = "40")
    private Float height;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "depth", example = "37")
    private Float depth;

    @JsonProperty("dimUnitCode")
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "dimUnitCode", example = "cm")
    private String dimUnitCode;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "weight", example = "0.46")
    private Float weight;

    @JsonProperty("weightUnitCode")
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "weightUnitCode", example = "g")
    private String weightUnitCode;

    @JsonProperty("itemUnitCode")
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "itemUnitCode", example = "EA")
    private String itemUnitCode;

    public ErpItemVolumeRequest validate() {
        itemCode = StringUtils.trim(itemCode);
        dimUnitCode = StringUtils.trim(dimUnitCode);
        weightUnitCode = StringUtils.trim(weightUnitCode);
        itemUnitCode = StringUtils.trim(itemUnitCode);

        return this;
    }
}
