package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

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
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Goods Code (상품코드)", example = "00953BL00002")
    private String godCd; //  "00953BL00002",
    @ApiModelProperty(notes = "Goods type (상품 종류)", example = "1")
    private String godType; //  "1",
    @ApiModelProperty(notes = "Goods type name (상품 종류 명)", example = "단품")
    private String godTypeNm; //  "단품",
    @ApiModelProperty(notes = "Add goods order quantity (추가 상품 주문 수량)", example = "1")
    private Integer addGodOrdQty; //  1,
    @ApiModelProperty(notes = "Goods Division (상품 분류: god: 상품, 추가상품: addGod)", example = "god")
    private String godDiv; //
    @ApiModelProperty(notes = "Out way (출고방식(1:선입선출,2:후입선출,3:유통기한지정))", example = "1")
    private String outWay; //  "1",
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(notes = "OrderLine Sequence (OrderLine-Item 시퀀스, 아이템별 1,2,3)", example = "1")
    private Integer itemSeq; //  "1",
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(notes = "Order quantity (주문 수량)", example = "3")
    private Integer ordQty; //  39,
    @ApiModelProperty(notes = "Lot No (랏번호)", example = "1213")
    private String lotNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Distribution Term Date (유통기한)", example = "20231231")
    private LocalDate distTermDt;
    @ApiModelProperty(notes = "Condition (상태)", example = "SERVICEABLE")
    private String condition;
    @ApiModelProperty(required = true, notes = "Box Division (박스 구분 -1 : 파스토 박스, 2 : 자사 박스, 4 : 파스토 폴리백, 5 : 자사 폴리백,7 : 파스토 스티로폼,  8 :자사 스티로폼)", example = "1")
    private String boxDiv;

    public void validate() {
        godCd = StringUtils.trim(godCd);
        godType = StringUtils.trim(godType);
        godTypeNm = StringUtils.trim(godTypeNm);
        godDiv = StringUtils.trim(godDiv);
        outWay = StringUtils.trim(outWay);
        lotNo = StringUtils.trim(lotNo);
        boxDiv = StringUtils.trim(boxDiv);
    }
}
