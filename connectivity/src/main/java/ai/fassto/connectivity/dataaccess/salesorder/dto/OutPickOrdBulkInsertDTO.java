package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPickOrdEntity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import nl.basjes.parse.useragent.PackagedRules;

@Getter
@ToString
public class OutPickOrdBulkInsertDTO {

    private String pickDt;
    private int pickSeq;
    private String whCd;
    private String outOrdSlipNo;
    private String cstCd;
    private String shopCd;
    private String ordNo;
    private Integer ordSeq;

    private String wrkStat;

    private boolean hasLotNo;

    private final String regId = "CONNECTIVITY";

    private final String updId = "CONNECTIVITY";

    private List<OutPickOrdEntity> outPickOrdEntityList;

    @Builder
    public OutPickOrdBulkInsertDTO(String pickDt, int pickSeq, String whCd, String outOrdSlipNo, String cstCd, String shopCd, String ordNo,
        Integer ordSeq, String wrkStat, boolean hasLotNo, List<OutPickOrdEntity> outPickOrdEntityList) {
        this.pickDt = pickDt;
        this.pickSeq = pickSeq;
        this.whCd = whCd;
        this.outOrdSlipNo = outOrdSlipNo;
        this.cstCd = cstCd;
        this.shopCd = shopCd;
        this.ordNo = ordNo;
        this.ordSeq = ordSeq;
        this.wrkStat = wrkStat;
        this.hasLotNo = hasLotNo;
        this.outPickOrdEntityList = outPickOrdEntityList;
    }
}
