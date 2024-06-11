package ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms;

import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class TmsFinalCallParcelRequest {
    @ApiModelProperty(required = true, notes = "Warehouse (Center) Code (창고(센터) 코드)", example = "TEST")
    private String warehouseCode; // "YI21",
    @ApiModelProperty(required = true, notes = "Customer Code (고객 코드)", example = "07970")
    private String customerCode; // "04365",
    @ApiModelProperty(notes = "Customer Code (고객 코드)", example = "테스트고객")
    private String customerName; // "파스토 용인2센터1층 테스트2",
    @ApiModelProperty(required = true, notes = "Delivery Company Code (CJ, LOTTE, HANJIN, CHAINLOGIS, TEAMFRESH, ECREMMOCE) (배송사코드)", example = "CJ")
    private String deliveryCompanyCode; // "HANJIN",
    @ApiModelProperty(notes = "Delivery Type (배송 종류)", example = "Domestic-24H Delivery")
    private String deliveryType; // "Domestic-24H Delivery",
    @ApiModelProperty(required = true, notes = "Changing Shipping Option", example = "Y")
    private String changingShippingOption; // "Y/N",
    @ApiModelProperty(required = true, notes = "Order No (주문번호)", example = "1547_23013100001")
    private String orderNo; // "test_order_no",
    @ApiModelProperty(required = true, notes = "Slip number (슬립 번호)", example = "1TESTOO230201000106")
    private String slipNo; // "test_order_no",
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(required = true, notes = "Pick Date (yyyy-MM-dd HH:mm:ss.SSS)", example = "2023-07-21 00:00:00.000")
    private LocalDateTime pickDate; // "20221111"
    @ApiModelProperty(required = true, notes = "Sender Name (보내는 사람)", example = "고우석")
    private String senderName; // "보내는사람",
    @ApiModelProperty(required = true, notes = "Sender Phone Number (보내는 사람 전화번호)", example = "010-1111-2222")
    private String senderPhone; // "010",
    @ApiModelProperty(notes = "Sender Phone Number ETC (보내는 사람 전화번호)", example = "010-2222-3333")
    private String senderPhoneEtc; // "",
    @ApiModelProperty(required = true, notes = "Sender ZIP Code (보내는 사람 우편번호)", example = "17180")
    private String senderZipCode; // "17180",
    @ApiModelProperty(required = true, notes = "Sender Address (보내는 사람 주소)", example = "경기 용인시 처인구 백암면 원설로 691 (고안리)")
    private String senderAddress; // "경기 용인시 처인구 백암면 원설로 691 (고안리)",
    @ApiModelProperty(required = true, notes = "Sender Address Detail (보내는 사람 주소 상세)", example = "102동 1123호")
    private String senderAddressDetail; // "용인2센터 1층/2층",
    @ApiModelProperty(required = true, notes = "Receiver Name (받는 사람)", example = "김광현")
    private String receiverName; // "파스토",
    @ApiModelProperty(required = true, notes = "Receiver Phone Number (받는 사람 전화번호)", example = "010-3333-4444")
    private String receiverPhone; // "02",
    @ApiModelProperty(notes = "Receiver Phone Number ETC (받는 사람 전화번호)", example = "010-4444-5555")
    private String receiverPhoneEtc; // "",
    @ApiModelProperty(notes = "Receiver ZIP Code (받는 사람 우편번호)", example = "05500")
    private String receiverZipCode; // "12345",
    @ApiModelProperty(required = true, notes = "Receiver Address (받는 사람 주소)", example = "서울특별시 송파구 올림픽로 25")
    private String receiverAddress; // "서울특별시 강남구 테헤란로79길 6",
    @ApiModelProperty(required = true, notes = "Receiver Address Detail (받는 사람 주소 상세)", example = "1층 문앞")
    private String receiverAddressDetail; // "9층",
    @ApiModelProperty(notes = "Ship Request Memo (배송시 요청사항)", example = "문 앞에 놔주세요")
    private String shipRequestMemo; // "문앞에 놔주세요",
    @ApiModelProperty(notes = "Entrance Password (공동현관 비밀번호)", example = "0101*")
    private String entrancePassword; // "",
    @ApiModelProperty(required = true, notes = "Box Quantity (박스 수량)", example = "1")
    private Integer boxQty; // 1
    @ApiModelProperty(notes = "Packing box info (패킹 박스 정보)")
    private List<Box> boxes;
}
