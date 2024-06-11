package ai.fassto.connectivity.dataaccess.workorder.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderRequestGdsEntity {
    private String slipNo;
    private String gdsDiv;
    private String gdsCd;
    private Integer gdsQty;
    private Integer completedQty;
    private Integer cancelledQty;
    private String usedOnsetSlipNo;
    private String regId;
    private String updId;
}
