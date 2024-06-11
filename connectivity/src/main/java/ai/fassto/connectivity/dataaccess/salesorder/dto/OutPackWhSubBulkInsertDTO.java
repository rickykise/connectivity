package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPackWhSubEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class OutPackWhSubBulkInsertDTO {
    private String packDt;
    private String cstCd;
    private String whCd;
    private Integer packSeq;
    private String outOrdSlipNo;
    private List<OutPackWhSubEntity> outPackWhSubEntityList;
}
