package ai.fassto.connectivity.dataaccess.workorder.repository.mybatis;

import ai.fassto.connectivity.dataaccess.workorder.dto.WorkOrderRequestGdsBulkUpdateDTO;
import ai.fassto.connectivity.dataaccess.workorder.dto.WorkOrderResponseBulkUpdateDTO;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderRequestMstEntity;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderResponseEntity;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderWrkStatHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkOrderMapper {
    int updateWorkStatus(WorkOrderRequestMstEntity workOrderRequestMstEntity);
    int updateBulkQty(WorkOrderResponseBulkUpdateDTO workOrderResponseBulkUpdateDTO);
    int insertWorkStatusHistory(WorkOrderWrkStatHistoryEntity workOrderWrkStatHistoryEntity);
    List<WorkOrderResponseEntity> findWorkOrderResponseList(String slipNo);
    boolean existsWorkOrderRequest(String slipNo);
}
