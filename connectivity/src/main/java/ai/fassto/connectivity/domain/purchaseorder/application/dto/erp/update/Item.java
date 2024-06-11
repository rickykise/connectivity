package ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.domain.common.exception.NotNullException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Item {

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Receipt Number (영수증번호)", example = "3314122")
    private String receiptNo;
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Sequence (시퀀스)", example = "1")
    private Integer sequence;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Code (코드)", example = "00953CHG-000001")
    private String code;
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Quantity (수량)", example = "22")
    private Integer qty;

    @ApiModelProperty(notes = "UOI (단위)", example = "EA")
    @JsonProperty("uOI")
    private String uoi_; // EA 고정일듯
    @ApiModelProperty(notes = "Base Quantity (개별 수량)", example = "220")
    private Integer baseQuantity; // 개별수량
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Condition (조건 - SERVICEABLE, UNSERVICEABLE, QAONHOLD)", example = "SERVICEABLE")
    private String condition;
    @ApiModelProperty(notes = "Memo")
    private String memo;
    @ApiModelProperty(notes = "Image Path (이미지 경로)")
    private String imagePath;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Expiry Date (유통기한 - yyyy-MM-dd HH:mm:ss.SSS)", example = "2023-11-01 00:00:00.000")
    private LocalDateTime expiryDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Make Date (재작일 - yyyy-MM-dd HH:mm:ss.SSS)", example = "2022-05-01 00:00:00.000")
    private LocalDateTime makeDate;
    @ApiModelProperty(notes = "Lot Number (랏 번호)", example = "21060201")
    private String lotNo;
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Barcode Quantity (바코드 수)", example = "80")
    private Integer barcodeQty;

    public void validate(String orderStatus) {
        receiptNo = StringUtils.trim(receiptNo);
        code = StringUtils.trim(code);
        uoi_ = StringUtils.trim(uoi_);
        condition = StringUtils.trim(condition);
        memo = StringUtils.trim(memo);
        imagePath = StringUtils.trim(imagePath);
        lotNo = StringUtils.trim(lotNo);

        if ("COMPLETED".equals(orderStatus)) {
            if (lotNo == null) {
                throw new NotNullException("item", "lotNo is null");
            }
        }

    }

}
