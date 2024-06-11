package ai.fassto.connectivity.domain.workorder.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.mapper.WorkOrderDataMapper;
import ai.fassto.connectivity.domain.workorder.application.port.output.external.api.wms.WorkOrderWms;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.domain.workorder.helper.update.WorkOrderUpdateRequestDefaultHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class WorkOrderUpdateRequestHandler {
    private final WorkOrderDataMapper workOrderDataMapper;
    private final WorkOrderWms workOrderWms;
    private final WorkOrderUpdateRequestDefaultHelper helper;

    public SoloChainWorkOrderResponse updateWorkOrder(SoloChainWorkOrderRequest request) {
        return workOrderDataMapper.workOrderToSoloChainWorkOrderResponse(
                workOrderWms.update(
                        workOrderDataMapper.soloChainWorkOrderRequestToWorkOrder(request, ActionType.UPDATE)
                )
        );
    }

    /**
     * 유통가공 작업상태(작업중, 완료, 취소) 변경
     * TB_WORK_ORDER_REQUEST - wrk_stat(작업상태) update
     * TB_WORK_ORDER_REQUEST_GDE - completed_qty(완료수량) update, cancelled_qty(취소수량) update
     * TB_WORK_ORDER_WRK_STAT_HISTORY - 작업상태 변경 후의 wrk_stat(작업상태) 이력 insert
     * **/
    public UpdateErpWorkOrderResponse active(UpdateErpWorkOrderRequest request){
        WorkOrder workOrder = workOrderDataMapper.updateErpWorkOrderRequestToWorkOrder(request);

        helper.persistWhenActive(workOrder);

        return workOrderDataMapper.workOrderToErpWorkOrderResponse(workOrder);
    }

    public UpdateErpWorkOrderResponse complete(UpdateErpWorkOrderRequest request){
        WorkOrder workOrder = workOrderDataMapper.updateErpWorkOrderRequestToWorkOrder(request);
        helper.persistWhenCompleted(workOrder);

        return workOrderDataMapper.workOrderToErpWorkOrderResponse(workOrder);
    }

    public UpdateErpWorkOrderResponse cancelled(UpdateErpWorkOrderRequest request){
        WorkOrder workOrder = workOrderDataMapper.updateErpWorkOrderRequestToWorkOrder(request);
        helper.persistWhenCancelled(workOrder);

        return workOrderDataMapper.workOrderToErpWorkOrderResponse(workOrder);
    }



}
