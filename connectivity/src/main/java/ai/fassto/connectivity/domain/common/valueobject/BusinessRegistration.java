package ai.fassto.connectivity.domain.common.valueobject;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class BusinessRegistration {
    private String businessRegistrationNo; // 사업자 등록번호 - busNo
    private String companyName; // 상호(법인 명) - supNm: 공급사명
    private String representativeName; // 성명(대표자) - ceoNm

    // 혼선의 여지가 있어서 legacy code 유지
    // Type of Business: 업종
    // items of business: 종목
    private String busSp; // 업태
    private String busTp; // 업종

    private String zipCode; // zipNo
    private String address1; // addr1
    private String address2; // addr2

    private String phoneNo;
    private String faxNo;
}
