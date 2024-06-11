package ai.fassto.connectivity.dataaccess.workorder.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderWrkStatHistoryEntity {
    private String slipNo;         //유통가공 전표 번호
    private String wrkStat;        //작업상태
    private String wrkStatRemark;  //작업상태 비고
    private String regId;          //등록자
    private String updId;          //수정자
}
