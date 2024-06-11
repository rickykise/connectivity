package ai.fassto.connectivity.dataaccess.workorder.adapter;

import ai.fassto.connectivity.domain.common.valueobject.id.BaseId;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.CommonMapper;
import ai.fassto.connectivity.dataaccess.workorder.mapper.WorkOrderDataAccessMapper;
import ai.fassto.connectivity.dataaccess.workorder.repository.mybatis.WorkOrderMapper;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.workorder.application.port.output.repository.WorkOrderRepository;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkOrderRepositoryImpl implements WorkOrderRepository {

    private final WorkOrderMapper workOrderMapper;
    private final WorkOrderDataAccessMapper workOrderDataAccessMapper;
    private final CommonMapper commonMapper;

    @Override
    public int updateWorkStatus(WorkOrder workOrder) {
        return workOrderMapper.updateWorkStatus(
                workOrderDataAccessMapper.workOrderToWorkOrderRequestMstEntity(workOrder)
        );
    }

    @Override
    public int updateBulkQtyWhenOrderLine(WorkOrder workOrder) {
        return workOrderMapper.updateBulkQty(
                workOrderDataAccessMapper.workOrderToWorkOrderResponseBulkUpdateDTOWhenOrderLine(workOrder)
        );
    }

    @Override
    public int updateBulkQtyWhenComponent(WorkOrder workOrder) {
        return workOrderMapper.updateBulkQty(
                workOrderDataAccessMapper.workOrderToWorkOrderResponseBulkUpdateDTOWhenComponent(workOrder)
        );
    }

    @Override
    public int insertWorkStatusHistory(WorkOrder workOrder) {
        return workOrderMapper.insertWorkStatusHistory(
                workOrderDataAccessMapper.workOrderToWorkOrderWrkStatHistoryEntity(workOrder)
        );
    }

    @Override
    public List<ItemSimple> findItemSimplesBy(List<ItemId> itemIdList) {
        List<String> godCdList = itemIdList.stream().map(BaseId::getValue).toList();
        return workOrderDataAccessMapper.godEntityListToItemSimpleList(commonMapper.findItems(godCdList));
    }

    @Override
    public List<WorkOrderResponse> findWorkOrderResponseBy(String slipNo) {
        return workOrderDataAccessMapper.workOrderResponseEntityToWorkOrderResponseList(
                workOrderMapper.findWorkOrderResponseList(slipNo)
        );
    }

    @Override
    public boolean existsWorkOrderRequest(String slipNo) {
        return workOrderMapper.existsWorkOrderRequest(slipNo);
    }


}
