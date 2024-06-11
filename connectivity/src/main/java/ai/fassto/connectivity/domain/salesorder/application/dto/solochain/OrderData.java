package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^(SalesOrder|SalesOrderSingleSKU|CarryOutOrder|ManualExpiry|RUSH)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
    @ApiModelProperty(required = true, notes = "Order Type (주문 종류 - SalesOrder: 출고, SalesOrderSingleSKU: item과 lot 이 하나일때 출고, CarryOutOrder:반출, ManualExpiry: lotNo 지정 출고, RUSH: 긴급)", example = "SalesOrder")
    private String orderType;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip Number (출고주문 요청번호)", example = "DT01OO221117000001")
    private String slipNo; //  "DT01OO221117000001",
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String whCd; //  "DT01",
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer Code (고객사 코드)", example = "01270")
    private String cstCd; //  "00953",
    @ApiModelProperty(notes = "Order division value ", example = "N")
    private String ordDivVal; //  "N",
    @ApiModelProperty(notes = "Front door password (현관문 비번)", example = "123456")
    private String frontDoorPwd; //  "",
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(notes = "Order Number (주문번호)", example = "123123123")
    private String ordNo; //  "123123123",
    @ApiModelProperty(notes = "Customer name (주문자 명", example = "홍길동")
    private String custNm; //  "ㅇㅇㅇㅇㅇㅇ",
    @ApiModelProperty(notes = "Customer telephone number (주문자 전화번호)", example = "010-2222-3333")
    private String custTelNo; //  "010-2222-3333",
    @ApiModelProperty(notes = "Customer telephone number etc (주문자 전화번호) 기타", example = "010-2222-3333")
    private String custTelNoEtc; //  "", TB_OUT_ORD_SHT.CUST_TEL_NO2
    @ApiModelProperty(notes = "Customer address (주문자 주소) 기타", example = "서울특별시 성동구 천호대로 400")
    private String custAddr; //  "서울특별시 성동구 천호대로 400",
    @ApiModelProperty(notes = "Customer address detail (주문자 상세 주소) 기타", example = "2층")
    private String custAddrDtl; //  2층
    @ApiModelProperty(notes = "Ship Request Term (배송 요청사항)", example = "안전한 배달 부탁드립니다.")
    private String shipReqTerm; //  "ㅇㄹㅇㄹㅇㄹㅇㄹ",
    @ApiModelProperty(notes = "Sender Name (보내는 사람 이름)", example = "STARBUCKS")
    private String sendNm; //  "이재호고객사",
    @ApiModelProperty(notes = "Sender telelphone number (보내는 사람 전화번호)", example = "01055606434")
    private String sendTelNo; //  "01055606434",
    @ApiModelProperty(notes = "Out way (출고방식(1:선입선출,2:후입선출,3:유통기한지정))", example = "1")
    private String outWay; //  "1",
    @ApiModelProperty(notes = "New remark - Instruction to center (센터 요청사항)", example = "포장시 종이를 두장으로 해 주세요")
    private String newRemark; //  instruction to center
    @ApiModelProperty(notes = "Zip code (주문자 우편번호) ", example = "04808")
    private String zipNo; //  "04808",
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Order date (출고 주문 일, 지시일자)", example = "20221229")
    private LocalDate ordDt; //  "20221117",
    @ApiModelProperty(notes = "Parcel Code (택배사 코드)", example = "CJ")
    private String parcelCd; // 택배사(tb_com_mst.main_cd = 'STD22')
    @ApiModelProperty(notes = "Invoice No (송장번호)", example = "Coupang2614752867r23")
    private String invoiceNo; // 송장번호
    @ApiModelProperty(notes = "Shipping Service Level Code (https://docs.google.com/spreadsheets/d/1GbEVUdBMJTlAAJa5e8Pb1HcwVLtwzmDK/edit#gid=911379770 참고)", example = "Domestic-24H Delivery")
    private String shippingServiceLevelCode;
    @ApiModelProperty(notes = "Shipping Service Level Code (https://docs.google.com/spreadsheets/d/1GbEVUdBMJTlAAJa5e8Pb1HcwVLtwzmDK/edit#gid=911379770 참고)", example = "24시간배달")
    private String shippingServiceLevelName;
    @ApiModelProperty(required = true, notes = "Customer Branded Box (ture: 고객사 박스, false: 파스토 박스)", example = "true")
    private String customerBrandedBox;
    @ApiModelProperty(required = true, notes = "Box Category (Bag:가방, Carton: 박스 상자, Pallet: 팔레트, Shippable: 배송 가능한 품목, Crate: 크레이트, Polybag: 폴리백, EPSStyrofoam: 스티로폼, CustomerCarton: 고객사 종이 박스, CustomerPolybag: 고객사 폴리백, CustomerEPSStyrofoam: 고객사 스티로폼)", example = "Carton")
    private String boxCategory;
    @ApiModelProperty(notes = "피킹시 지시사항", example = "파스토테스트빠른피킹부탁..")
    private String pickingInstruction;
    @ApiModelProperty(notes = "패킹시 지시사항", example = "친환경 크라프트 테이프로 포장해주세요.")
    private String packingInstruction;
    @ApiModelProperty(notes = "출고시 지시사항", example = "파스토테스트빠른배송부탁..")
    private String shippingInstruction;


    public void validate() {
        orderType = StringUtils.trim(orderType);
        slipNo = StringUtils.trim(slipNo);
        whCd = StringUtils.trim(whCd);
        cstCd = StringUtils.trim(cstCd);
        ordDivVal = StringUtils.trim(ordDivVal);
        frontDoorPwd = StringUtils.trim(frontDoorPwd);
        ordNo = StringUtils.trim(ordNo);
        custNm = StringUtils.trim(custNm);
        custTelNo = StringUtils.trim(custTelNo);
        custTelNoEtc = StringUtils.trim(custTelNoEtc);
        custAddr = StringUtils.trim(custAddr);
        custAddrDtl = StringUtils.trim(custAddrDtl);
        shipReqTerm = StringUtils.trim(shipReqTerm);
        sendNm = StringUtils.trim(sendNm);
        sendTelNo = StringUtils.trim(sendTelNo);
        outWay = StringUtils.trim(outWay);
        newRemark = StringUtils.trim(newRemark);
        zipNo = StringUtils.trim(zipNo);
        parcelCd = StringUtils.trim(parcelCd);
        invoiceNo = StringUtils.trim(invoiceNo);
        shippingServiceLevelCode = StringUtils.trim(shippingServiceLevelCode);
        shippingServiceLevelName = StringUtils.trim(shippingServiceLevelName);
        customerBrandedBox = StringUtils.trim(customerBrandedBox);
        boxCategory = StringUtils.trim(boxCategory);
        pickingInstruction = StringUtils.trim(pickingInstruction);
        packingInstruction = StringUtils.trim(packingInstruction);
        shippingInstruction = StringUtils.trim(shippingInstruction);
    }
}
