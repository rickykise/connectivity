package ai.fassto.connectivity.externalservice.carrier.tms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class TmsFinalCallRequest {
    private String warehouseCode; // "YI21",
    private String customerCode; // "04365",
    private String customerName; // "파스토 용인2센터1층 테스트2",
    private String deliveryCompanyCode; // "HANJIN",
    private String deliveryType; // "Domestic-24H Delivery",
    private String changingShippingOption; // "Y/N",
    private String orderNo; // "test_order_no",
    private String slipNo; // "test_order_no",
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate pickDate; // "20221111"
    private String senderName; // "보내는사람",
    private String senderPhone; // "010",
    private String senderPhoneEtc; // "",
    private String senderZipCode; // "17180",
    private String senderAddress; // "경기 용인시 처인구 백암면 원설로 691 (고안리)",
    private String senderAddressDetail; // "용인2센터 1층/2층",
    private String receiverName; // "파스토",
    private String receiverPhone; // "02",
    private String receiverPhoneEtc; // "",
    private String receiverZipCode; // "12345",
    private String receiverAddress; // "서울특별시 강남구 테헤란로79길 6",
    private String receiverAddressDetail; // "9층",
    private String shipRequestMemo; // "문앞에 놔주세요",
    private String entrancePassword; // "",
    private Integer boxQty; // 2
    private List<Box> boxes;
}
