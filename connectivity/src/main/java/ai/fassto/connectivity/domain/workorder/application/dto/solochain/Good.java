package ai.fassto.connectivity.domain.workorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PositiveGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(notes = "OrderLine Sequence (OrderLine-Item 시퀀스, 아이템별 1,2,3)", example = "1")
    private Integer itemSeq; //  "1",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Goods Division [상품구분: 1(to) 2(from)]", example = "1")
    private String gdsDiv; //   "1"

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Goods Code (상품코드)", example = "01270HA00008")
    private String gdsCd; //   "01270HA00008"

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Order Quantity (주문 수량)", example = "3")
    private Integer ordQty; //  3

    @ApiModelProperty(required = true, notes = "Lot Number (랏 번호)", example = "01270HA00008")
    private String lotNo; //   "01270HA00008"

    @ApiModelProperty(required = true, notes = "Distribution Term Date (유통기한)", example = "20231231")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate distTermDt; //   "01270HA00008"


    public void validate() {
        gdsDiv = StringUtils.trim(gdsDiv);
        gdsCd = StringUtils.trim(gdsCd);
        lotNo = StringUtils.trim(lotNo);
    }
}
