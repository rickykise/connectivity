package ai.fassto.connectivity.dataaccess.workorder.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderRequestMstEntity {
    private String whCd;
    private String slipNo;
    private String requestYmd;
    private String cstCd;
    private String wrkDiv;
    private String wrkStat;
    private String workOrderNm;
    private Integer fileSeq;
    private String memo;
    private String regId;
    private String updId;
}
