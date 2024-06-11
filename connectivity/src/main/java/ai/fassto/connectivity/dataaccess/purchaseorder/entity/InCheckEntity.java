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
public class InCheckEntity {

    private String inDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String supCd;
    private String locNo;
    private String luNo;
    private String lotNo;
    private String godCd;
    private Integer inQty;
    private Integer inQtyScanCnt;
    private String inDiv;
    private Integer inPr;
    private String inWay;
    private String inOrdSlipNo;
    private Integer inOrdSeq;
    private String inTp;
    private Integer pltCnt;
    private Integer weight5;
    private Integer weight10;
    private Integer weight15;
    private Integer weight20;
    private Integer weight20Over;
    private Integer subBoxCnt;
    private String mixInYn;
    private Integer mixInCnt;
    private Integer godBarcdAttCnt;
    private String inArvDt;
    private String inArvTime;
    private Integer inWrkPer;
    private Integer inWrkTime;
    private Integer payTime;
    private Integer inRtnPay;
    private String regGear;
    private Integer ordSeq;
    private String rateGbn;

}
