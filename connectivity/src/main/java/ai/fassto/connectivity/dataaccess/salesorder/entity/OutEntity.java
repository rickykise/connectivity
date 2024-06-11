package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutEntity {
    private String outDt;          //출고일자
    private String whCd;           //창고코드
    private String slipNo;         //전표번호
    private String cstCd;          //고객사코드
    private String shopCd;         //출고처코드
    private String locNo;          //로케이션번호
    private String luNo;           //LU번호
    private String lotNo;          //LOT번호
    private String godCd;          //상품코드
    private Integer outQty;        //출고수량
    private String outDiv;         //출고구분(1:택배,2:차량/기타,3:해외,4:해외(이크레모스),5:Express(UPS),6:Standard(한진특송),7:Standard(이커머스올)),8:방문수령
    private String outOrdSlipNo;   //출고지시전표번호
    private String ordNo;          //주문번호
    private Integer ordSeq;        //주문순번
    private String outTp;          //출고유형(1:정상(출고),2:고객사재고이관,3:기타출고(반출),9:예외)
    private String remark;         //비고
    private String delayCd;        //출고대기코드(IN:출고대기입고 OUT:출고완료 CANCEL:출고취소 )
    private String delayLocNo;
    private String migTime;
    private String godDiv;
}
