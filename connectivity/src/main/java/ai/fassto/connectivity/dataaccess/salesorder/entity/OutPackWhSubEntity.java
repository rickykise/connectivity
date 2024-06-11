package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutPackWhSubEntity {
    private String packDt;
    private String whCd;
    private Integer packSeq;
    private String outOrdSlipNo;
    private String cstCd;
    private Integer packQty;
    private Integer whIdx;
}
