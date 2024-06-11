package ai.fassto.connectivity.dataaccess.workorder.dto;

import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderRequestGdsEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class WorkOrderRequestGdsBulkUpdateDTO {
    private String slipNo;
    private String updId;
    private List<WorkOrderRequestGdsEntity> gdsEntityList;
}
