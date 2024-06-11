package ai.fassto.connectivity.domain.workorder.application.dto.erp.update;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public record UpdateErpWorkOrderRequest(
        @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
        @ApiModelProperty(required = true, notes = "Slip Number (작업 식별 고유번호)", example = "TESTIO221109007")
        String slipNo,

        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
        @ApiModelProperty(required = true, notes = "WorkDate (상태변경일)", example = "2022-12-22 09:01:05")
        LocalDateTime workDate,

        @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
        @Pattern(regexp = "^(ACTIVE|COMPLETED|CANCELLED)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
        @ApiModelProperty(required = true, notes = "Work Status (작업상태) - ACTIVE:작업중, COMPLETED:완료, CANCELLED:취소", example = "COMPLETED")
        String workStatus,

        @ApiModelProperty(required = true, notes = "Memo (보류시 사유입력하는 메모)", example = "")
        String memo,

        @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
        @Pattern(regexp = "^(KITTING|UNKITTING|STICKER|TAG|IRONING)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
        @ApiModelProperty(required = true, notes = "Work Type (유통가공 작업 구분)", example = "KITTING")
        String workType,

        @ApiModelProperty(required = true, notes = "workerTime (유통가공 작업 시간)", example = "90")
        String workerTime,

        @ApiModelProperty(required = true, notes = "workerCnt (유통가공 작업 인원)", example = "3")
        String workerCnt,

        @Valid
        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
        @ApiModelProperty(required = true, notes = "OrderLine list (오더라인 리스트)")
        List<OrderLine> orderLines,

        @Valid
        @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
        @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
        @ApiModelProperty(required = true, notes = "Component list (컴포넌트 리스트)")
        List<Component> components
) {
    public UpdateErpWorkOrderRequest validate() {
        return this;
    }
}
