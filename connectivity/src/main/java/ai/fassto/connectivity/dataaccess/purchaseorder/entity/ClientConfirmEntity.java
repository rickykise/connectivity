package ai.fassto.connectivity.dataaccess.purchaseorder.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfirmEntity {
    private String receiptNo;               //영수증번호
    private String whCd;                    //창고코드
    private String slipNo;                  //전표번호
    private String cstCd;                   //고객사코드
    private String godCd;                   //상품코드
    private String clientConfirmMemo;       //고객확인내용
    private String clientConfirmImgPath;    //고객확인이미지경로
}
