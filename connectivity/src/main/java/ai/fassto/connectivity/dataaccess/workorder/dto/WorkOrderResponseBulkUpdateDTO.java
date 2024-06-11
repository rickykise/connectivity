package ai.fassto.connectivity.dataaccess.workorder.dto;

import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderResponseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class WorkOrderResponseBulkUpdateDTO {
    private String slipNo;
    private String updId;
    private LocalDateTime workDate;
    private Integer workerTime;
    private Integer workerCnt;
    private List<WorkOrderResponseEntity> workOrderResponseEntityList;
}
