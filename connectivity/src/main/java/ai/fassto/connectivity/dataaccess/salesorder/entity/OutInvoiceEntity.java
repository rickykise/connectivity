package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutInvoiceEntity {
    private String outDt;          //출고일자
    private String whCd;           //창고코드
    private String slipNo;         //전표번호
    private String cstCd;          //고객사코드
    private String shopCd;         //출고처코드
    private String godCd;          //상품코드
    private String godDiv;         //상품종류
    private String invoiceNo;      //송장번호
    private Integer outQty;        //출고수량

}
