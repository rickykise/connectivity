package ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class OutData {
    @ApiModelProperty(notes = "Requests for dispatch (배차시 요청 사항)")
    private String description; // "",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Loading Unit (PLT, Bulk) (상차단위 (PLT, Bulk))", example = "PLT")
    private String loadUnit; // "PLT",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Quantity per unit (단위별 수량)", example = "10")
    private String loadCnt; // "10",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Estimated weight (예상 중량)", example = "1.02t")
    private String loadWeight; // 1.02t",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Loading manager name (상차담당자명)", example = "이예지")
    private String loadName; // "이예지",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Loading manager phone number (상차담당자 전화번호)", example = "010-3333-4444")
    private String loadPhone; // "010-3333-4444",

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(required = true, notes = "Loading Date Time (상차 날짜 시간)")
    private LocalDateTime loadDate; // ""

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Loading manager email (상차 담당자 이메일)")
    private String loadEmail; // ""
}
