package ai.fassto.connectivity.dataaccess.workorder.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderResponseEntity {
    private String whCd;
    private String slipNo;
    private Integer itemSeq;
    private String gdsCd;
    private String lotNo;
    private String delYn;
    private String gdsDiv;
    private String wrkDiv;
    private String workYmd;
    private Integer orderQty;
    private Integer completedQty;
    private Integer cancelledQty;
    private String updId;
}
