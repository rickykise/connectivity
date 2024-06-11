package ai.fassto.connectivity.dataaccess.purchaseorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    private String receiptNo;   //영수증번호
    private String godCd;
    private Integer ordSeq;
    private Integer qty;
    private String status;
    private String clientConfirmMemo;    //고객확인내용
    private String clientConfirmImgPath; //고객확인이미지경로
    private String distTermDt;
    private String makeDt;
    private String lotNo;
    private Integer inPr;
    private String locNo;
    private String luNo;
    private Integer godBarcdAttCnt;
}
