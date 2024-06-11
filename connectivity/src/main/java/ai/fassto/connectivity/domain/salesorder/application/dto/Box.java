package ai.fassto.connectivity.domain.salesorder.application.dto;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.domain.common.exception.NotBlankException;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.CourierType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus.SHIPPED;

@Getter
@Setter
@NoArgsConstructor
public class Box {
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Box Sequence (박스 시퀀스)", example = "1")
    private Integer boxSeq;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Box ID (박스 아이디)", example = "fb1a41")
    private String boxId;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Category (박스 카테고리)", example = "Carton")
    private String category;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "boxType (박스 종류)", example = "L")
    private String boxType;
    @ApiModelProperty(required = true, notes = "Courier Code (택배사 코드)", example = "CJ")
    private String courierCode;
    @ApiModelProperty(required = true, notes = "Invoice Number (인보이스 번호)", example = "346726734884")
    private String invoiceNo;
    @ApiModelProperty(notes = "Basic Fare (계약 운임)", example = "0")
    private Integer basicFare;
    @ApiModelProperty(notes = "Deal Fare (취급 운임)", example = "1000")
    private Integer dealFare;
    @ApiModelProperty(notes = "Air Fare (항공 운임)", example = "1000")
    private Integer airFare;
    @ApiModelProperty(notes = "Ship Fare (선박 운임)", example = "0")
    private Integer shipFare;
    @ApiModelProperty(notes = "RealPackingVideo (리얼패킹 영상)", example = "WarehouseYI123YI23OO23041200022431415000")
    private String realPackingVideo;

    public void validate(String orderStatus) {
        boxId = StringUtils.trim(boxId);
        category = StringUtils.trim(category);
        boxType = StringUtils.trim(boxType);
        courierCode = StringUtils.trim(courierCode);
        invoiceNo = StringUtils.trim(invoiceNo);


        if (SHIPPED.is(orderStatus) &&
                CourierType.isCourier(courierCode) &&
                StringUtils.isBlank(invoiceNo)) {
            throw new NotBlankException("invoiceNo", "invoiceNo is null");
        }

    }
}