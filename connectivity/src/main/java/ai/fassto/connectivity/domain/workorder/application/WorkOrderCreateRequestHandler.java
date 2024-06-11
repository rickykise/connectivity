package ai.fassto.connectivity.domain.workorder.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.mapper.WorkOrderDataMapper;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.port.output.external.api.wms.WorkOrderWms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkOrderCreateRequestHandler {
    private final WorkOrderDataMapper workOrderDataMapper;
    private final WorkOrderWms workOrderWms;

    public SoloChainWorkOrderResponse createWorkOrder(SoloChainWorkOrderRequest request) {
        return workOrderDataMapper.workOrderToSoloChainWorkOrderResponse(
                workOrderWms.create(
                        workOrderDataMapper.soloChainWorkOrderRequestToWorkOrder(request, ActionType.CREATE)
                )
        );
    }
}
