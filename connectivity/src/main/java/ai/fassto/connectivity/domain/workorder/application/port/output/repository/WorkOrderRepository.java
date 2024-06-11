package ai.fassto.connectivity.domain.workorder.application.port.output.repository;

import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrderResponse;

import java.util.List;

public interface WorkOrderRepository {
    int updateWorkStatus(WorkOrder workOrder);
    int updateBulkQtyWhenOrderLine(WorkOrder workOrder);
    int updateBulkQtyWhenComponent(WorkOrder workOrder);
    int insertWorkStatusHistory(WorkOrder workOrder);
    List<ItemSimple> findItemSimplesBy(List<ItemId> itemIdList);
    List<WorkOrderResponse> findWorkOrderResponseBy(String slipNo);
    boolean existsWorkOrderRequest(String slipNo);

}
