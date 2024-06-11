package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutInventoryAssignEntity {
    private String outOrdSlipNo; //출고요청전표번호
    private Integer itemSeq;   //전표번호 내 아이템 순번
    private String whCd;     //창고코드
    private String lotNo;    //LOT 번호
    private String godCd;    //상품코드
    private Integer assignQty;   //할당수량
    private String DEL_YN;  //삭제여부
    private String mapSlipNo;
}
