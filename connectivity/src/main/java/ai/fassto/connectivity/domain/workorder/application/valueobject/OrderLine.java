package ai.fassto.connectivity.domain.workorder.application.valueobject;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record OrderLine(
        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "오더라인 순서", example = "1")
        Integer sequence,

        @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "code (상품코드)", example = "01328TESTGOD1")
        String code,

        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "요청수량", example = "300")
        Integer orderQty,

        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "완료된 수량", example = "50")
        Integer completedQty,

        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "남은 수량", example = "50")
        Integer remainingQty,

        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "취소된 수량", example = "0")
        Integer cancelledQty
) {
}
