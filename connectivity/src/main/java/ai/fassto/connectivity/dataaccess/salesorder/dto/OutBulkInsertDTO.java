package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OutBulkInsertDTO {
    private String outDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String shopCd;
    private String outOrdSlipNo;
    private String ordNo;
    private Integer ordSeq;
    private Integer outType;
    private List<OutEntity> outEntityList;

    @Builder
    public OutBulkInsertDTO(String outDt, String whCd, String slipNo, String cstCd, String shopCd, String outOrdSlipNo, String ordNo, Integer ordSeq, Integer outType, List<OutEntity> outEntityList) {
        this.outDt = outDt;
        this.whCd = whCd;
        this.slipNo = slipNo;
        this.cstCd = cstCd;
        this.shopCd = shopCd;
        this.outOrdSlipNo = outOrdSlipNo;
        this.ordNo = ordNo;
        this.ordSeq = ordSeq;
        this.outType = outType;
        this.outEntityList = outEntityList;
    }
}
