package ai.fassto.connectivity.domain.workorder.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.port.input.service.WorkOrderApplicationService;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderNotSupportException;
import ai.fassto.connectivity.domain.workorder.enums.WorkOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static ai.fassto.connectivity.domain.workorder.enums.WorkOrderStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkOrderApplicationServiceImpl implements WorkOrderApplicationService {
    private final WorkOrderCreateRequestHandler workOrderCreateRequestHandler;
    private final WorkOrderUpdateRequestHandler workOrderUpdateRequestHandler;
    private final WorkOrderDeleteRequestHandler workOrderDeleteRequestHandler;

    @Override
    public SoloChainWorkOrderResponse create(SoloChainWorkOrderRequest request) {
        return workOrderCreateRequestHandler.createWorkOrder(request);
    }

    @Override
    public SoloChainWorkOrderResponse update(SoloChainWorkOrderRequest request) {
        return workOrderUpdateRequestHandler.updateWorkOrder(request);
    }

    @Override
    public SoloChainWorkOrderResponse delete(SoloChainWorkOrderRequest request) {
        return workOrderDeleteRequestHandler.deleteWorkOrder(request);
    }

    @Override
    public UpdateErpWorkOrderResponse update(UpdateErpWorkOrderRequest request) {
        WorkOrderStatus status = WorkOrderStatus.findBy(request.workStatus());
        if (status.equals(ACTIVE)){
            return workOrderUpdateRequestHandler.active(request);
        } else if (status.equals(COMPLETED)) {
            return workOrderUpdateRequestHandler.complete(request);
        } else if (status.equals(CANCELLED)) {
            return workOrderUpdateRequestHandler.cancelled(request);
        } else {
            throw new WorkOrderNotSupportException(new ErrorDetail("WorkOrderStatus", status.name()));
        }
    }
}
