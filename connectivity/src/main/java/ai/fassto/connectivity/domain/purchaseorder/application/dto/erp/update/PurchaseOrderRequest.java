package ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update;

import ai.fassto.connectivity.domain.common.exception.NotNullException;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PositiveGroup;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType;
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
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class PurchaseOrderRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer code (고객사 코드)", example = "12345")
    private String cstCd;

    // GOD_CD = CST_CD + CST_GOD_CD
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Item code (상품 코드)", example = "123458801942000057")
    private String godCd;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Order date (입고 주문 일)", example = "20221229")
    private LocalDate ordDt;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse code (창고 코드)", example = "YI01")
    private String whCd;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip number (슬립 번호)", example = "TESTIO221229001")
    private String slipNo;
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(notes = "OrderLine Sequence (OrderLine-Item 시퀀스, 아이템별 1,2,3)", example = "1")
    private Integer itemSeq; //  "1",
    @ApiModelProperty(notes = "Order quantity (주문 수량)", example = "30")
    private Integer ordQty;
    // INP01
    @ApiModelProperty(notes = "In way[VehicleType] (입고방식:01-택배, 02-차량)", example = "02")
    private String inWay;
    @ApiModelProperty(notes = "In way Name[VehicleType] (입고방식:courier-택배, vehicle-차량)", example = "vehicle")
    private String inWayName;
    @ApiModelProperty(notes = "Parcel company (택배사)", example = "한진")
    private String parcelComp;
    @ApiModelProperty(notes = "Parcel invoice Number(택배송장번호)", example = "321120201111")
    private String parcelInvoiceNo;
    @ApiModelProperty(notes = "Remark (입고 비고/ 차량반품 입고요청사항/ 센터반품 비고)", example = "30")
    private String remark;
    @ApiModelProperty(notes = "Courier name (운송사 명/ 차량반품 운송사명)", example = "엑셀차")
    private String courierName;
    @ApiModelProperty(notes = "License Plate number (자동차 등록 번호)", example = "99허1111")
    private String carNumber;
    @ApiModelProperty(notes = "Driver contact (차량반품 운송기사님연락처)", example = "99허1111")
    private String courierDriverPhoneNumber;
    @ApiModelProperty(notes = "Release Type (출고 가능 타입 0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고)", example = "2")
    private String releaseGbn; // release GuBun no
    @ApiModelProperty(notes = "Release Type Name (0: 당일출고,(D + 0), 1: 익일출고 (D + 1), 2: 일반출고 (D + 2))", example = "2: 일반출고 (D + 2)")
    private String releaseGbName; // release GuBun no

    @ApiModelProperty(notes = "Return Quantity (반품 수량)", example = "1")
    private Integer rtnQty;
    @ApiModelProperty(notes = "Return type (01:반품, 02: 교환, 03:환불)", example = "01")
    private String rtnGubun;
    @ApiModelProperty(notes = "Return type Name (return:반품, exchange: 교환, return:환불)", example = "return")
    private String rtnGubunName;

    @ApiModelProperty(notes = "Original Invoice Number(원출고요청번호)", example = "321120201111")
    private String invoiceNo;
    @ApiModelProperty(notes = "Return Parcel Code(반품 택배사)", example = "CJ")
    private String parcelCd;
    @ApiModelProperty(notes = "Return Ship Request Term(반품 배송메세지)", example = "불량상품")
    private String shipReqTerm;
    @ApiModelProperty(notes = "Original Parcel Company (센터반품 원택배사)", example = "한진")
    private String orgParcelComp;
    @ApiModelProperty(notes = "Original tracking number (센터반품 원택배송장번호)", example = "321120201112")
    private String orgInvoiceNo;

    @ApiModelProperty(notes = "Vendor code (공급사 코드)", example = "123450001")
    private String supCd;

    public void validate(String orderType, int index) {
        cstCd = StringUtils.trim(cstCd);
        godCd = StringUtils.trim(godCd);
        whCd = StringUtils.trim(whCd);
        slipNo = StringUtils.trim(slipNo);
        inWay = StringUtils.trim(inWay);
        inWayName = StringUtils.trim(inWayName);
        parcelComp = StringUtils.trim(parcelComp);
        parcelInvoiceNo = StringUtils.trim(parcelInvoiceNo);
        remark = StringUtils.trim(remark);
        courierName = StringUtils.trim(courierName);
        carNumber = StringUtils.trim(carNumber);
        courierDriverPhoneNumber = StringUtils.trim(courierDriverPhoneNumber);
        releaseGbn = StringUtils.trim(releaseGbn);
        releaseGbName = StringUtils.trim(releaseGbName);
        rtnGubun = StringUtils.trim(rtnGubun);
        rtnGubunName = StringUtils.trim(rtnGubunName);
        invoiceNo = StringUtils.trim(invoiceNo);
        parcelCd = StringUtils.trim(parcelCd);
        shipReqTerm = StringUtils.trim(shipReqTerm);
        orgParcelComp = StringUtils.trim(orgParcelComp);
        orgInvoiceNo = StringUtils.trim(orgInvoiceNo);
        supCd = StringUtils.trim(supCd);

        if (PurchaseOrderType.findByType(orderType) == PurchaseOrderType.PURCHASE_ORDER) {
            if (ordQty == null) {
                throw new NotNullException(String.format("dataList[%d].ordQty", index), null);
            } else if (ordQty < 1) {
                throw new NotNullException(String.format("dataList[%d].ordQty", index), "0");
            }
        } else {
            if (rtnQty == null) {
                throw new NotNullException(String.format("dataList[%d].rtnQty", index), null);
            } else if (rtnQty < 1) {
                throw new NotNullException(String.format("dataList[%d].rtnQty", index), "0");
            }
        }
    }
}