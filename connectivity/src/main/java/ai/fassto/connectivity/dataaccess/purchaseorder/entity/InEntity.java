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
public class InEntity {

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
    private String inDiv;
    private Integer inPr;
    private String inWay;
    private String carKind;
    private Integer pltCnt;
    private String inOrdSlipNo;
    private Integer inOrdSeq;
    private String regGear;
    private String rtnGodCheckStat;
    private String remark;
    private String cstMemo;
    private Integer ordSeq;

}
