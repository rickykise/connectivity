package ai.fassto.connectivity.dataaccess.purchaseorder.dto;

import ai.fassto.connectivity.dataaccess.purchaseorder.entity.InCheckEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InCheckBulkInsertDTO {

    private String inDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String supCd;
    private String locNo;
    private String inDiv;
    private String inWay;
    private String inOrdSlipNo;
    private String inTp;
    private int pltCnt;
    private Integer weight5;
    private Integer weight10;
    private Integer weight15;
    private Integer weight20;
    private Integer weight20Over;
    private int subBoxCnt;
    private String mixInYn;
    private int mixInCnt;
    private int godBarcdAttCnt;
    private String inArvDt;
    private String inArvTime;
    private int inWrkPer;
    private int inWrkTime;
    private int payTime;
    private int inRtnPay;
    private String regGear;
    @Setter
    private Integer inOrdSeq;

    private List<InCheckEntity> inCheckEntityList;

}
