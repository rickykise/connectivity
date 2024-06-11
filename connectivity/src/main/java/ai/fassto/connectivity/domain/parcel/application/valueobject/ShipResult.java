package ai.fassto.connectivity.domain.parcel.application.valueobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ShipResult(
        String warehouseCode, // "YI21",
        String customerCode, // "04365",
        String customerName, // "파스토 용인2센터1층 테스트2",
        String deliveryCompanyCode, // "HANJIN",
        String deliveryType, // "HANJIN",
        String changingShippingOption, // "Y/N",
        String orderNo, // "test_order_no",
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
        LocalDateTime pickDate, // "20221111",
        String senderName, // "보내는사람",
        String senderPhone, // "010-1234-5678",
        String senderPhoneEtc, // "",
        String senderZipCode, // "17180",
        String senderAddress, // "경기 용인시 처인구 백암면 원설로 691 (고안리)",
        String senderAddressDetail, // "용인2센터 1층/2층",
        String receiverName, // "파스토",
        String receiverPhone, // "02-1234-5678",
        String receiverPhoneEtc, // "",
        String receiverZipCode, // "12345",
        String receiverAddress, // "서울특별시 강남구 테헤란로79길 6",
        String receiverAddressDetail, // "9층",
        String shipRequestMemo, // "문앞에 놔주세요",
        String entrancePassword, // "",
        List<Package> Packages // [{
) {
}
