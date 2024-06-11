package ai.fassto.connectivity.domain.stock.application.dto.erp.update;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class Item {

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "YI21")
    private String warehouseCode; //  "YI02_3F",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Condition (재고 상태: SERVICEABLE, QAONHOLD, UNSERVICEABLE)", example = "SERVICEABLE")
    private String condition; //  "Serviceable", "UnServiceable"

    @ApiModelProperty(notes = "Lot Number", example = "22090501")
    private String lotNo; //  "22090501",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Goods Code (상품 코드)", example = "01328CSTBOX01")
    private String godCd; //  "01328CSTBOX01",

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Stock Quantity (재고 변경 수량)", example = "-1")
    private Integer stockQty; // -1

    @ApiModelProperty(notes = "adjustmentReasonCode - Valid reason codes in Solochain (재고변경 사유)")
    private String adjustmentReasonCode;

    @ApiModelProperty(notes = "Move Type - When a movement is generated during this adjustment, send the type of movement. (이동 종류)")
    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Expiry Date (유통기한)", example = "2023-01-13 00:00:00.000")
    private LocalDateTime expiryDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Make Date (제조일자)", example = "2022-05-01 00:00:00.000")
    private LocalDateTime makeDate;

    public void validate() {
        this.warehouseCode = StringUtils.trim(warehouseCode);
        this.condition = StringUtils.trim(condition);
        this.lotNo = StringUtils.trim(lotNo);
        this.godCd = StringUtils.trim(godCd);
        this.adjustmentReasonCode = StringUtils.trim(adjustmentReasonCode);
        this.type = StringUtils.trim(type);
    }

}