package ai.fassto.connectivity.domain.workorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoloChainWorkOrderRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip Number (입고요청번호)", example = "DT01F221214002")
    private String slipNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String whCd; //  "DT01",

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    @ApiModelProperty(required = true, notes = "Request Date (요청일)", example = "20221214")
    private LocalDate requestYmd; //  "20221214"

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer Code (고객사 코드)", example = "01270")
    private String cstCd; //  "01270"

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Work Division (작업 구분 - KITTING, STICKER, TAG, IRONING)", example = "KITTING")
    private String wrkDiv;

    @ApiModelProperty(required = true, notes = "memo", example = "이렇게 작업해주세요")
    private String memo; //  "이렇게 작업해주세요",

    @ApiModelProperty(required = true, notes = "file", example = "")
    private String file; //  "",

    @Valid
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @ApiModelProperty(required = true, notes = "Work Order list (유통가공작업 상품 리스트)")
    private List<Good> gdsList;

    public SoloChainWorkOrderRequest validate() {
        slipNo = StringUtils.trim(slipNo);
        whCd = StringUtils.trim(whCd);
        cstCd = StringUtils.trim(cstCd);
        wrkDiv = StringUtils.trim(wrkDiv);
        memo = StringUtils.trim(memo);
        file = StringUtils.trim(file);

        for (Good good : gdsList) {
            good.validate();
        }

        return this;
    }
}
