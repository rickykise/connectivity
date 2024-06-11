package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import lombok.Builder;

@Builder
public record Contact(
        String name,
        String telephoneNo, // 주문자 전화번호
        String telephoneNoEtc, // 주문자 전화번호 기타
        String address1,
        String address2,
        String zipCode,
        String frontDoorPassword,
        String instruction, // 요청사항(remark)
        String pickingInstruction, // 피킹시 요청사항
        String packingInstruction, // 패킹시 요청사항
        String shippingInstruction // 출고시 요청사항
) {
}
