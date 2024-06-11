package ai.fassto.connectivity.dataaccess.purchaseorder.entity;

import java.time.LocalDateTime;
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
public class InWrkInfoEntity {

    private String ordDt;
    private String whCd;
    private String slipNo;
    private Integer seq;
    private String cstCd;
    private String inArvDt;
    private String inArvTime;
    private Integer inWrkPer;
    private Integer inWrkTime;
    private String inWrkCd;
    private String inRtnPayCd;
    private String custNm;
    private Integer inRtnPay;
    private Integer payTime;
    private Integer pltCnt;
    private Integer boxCnt;
    private Integer subBoxCnt;
    private String mixInYn;
    private Integer godBarcdAttCnt;
    private String rateGbn;
    private String confId;
    private LocalDateTime confTime;
    private Integer weight5;
    private Integer weight10;
    private Integer weight15;
    private Integer weight20;
    private Integer weight20Over;
    private Integer mixInCnt;
    private Integer hardWorkCost;
    private String inWay;
    private String releaseGbn;
    private String regId;
}