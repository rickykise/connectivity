package ai.fassto.connectivity.domain.salesorder.application.dto;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class Consumable {

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(notes = "Sequence (시퀀스)", example = "1")
    private Integer sequence;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "부자재 번호", example = "CB_0008")
    private String code;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(notes = "부자재 수량", example = "10")
    private Integer qty;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "부자재 사이즈 코드(STD50)", example = "3XL")
    private String size;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "부자재 구분(STD49)", example = "CB_00001")
    private String type;

    public void validate() {
        code = StringUtils.trim(code);
        size = StringUtils.trim(size);
        type = StringUtils.trim(type);
    }
}
