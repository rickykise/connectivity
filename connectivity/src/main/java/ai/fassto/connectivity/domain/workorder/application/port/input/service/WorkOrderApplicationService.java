package ai.fassto.connectivity.domain.workorder.application.port.input.service;


import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse;

public interface WorkOrderApplicationService {

    SoloChainWorkOrderResponse create(SoloChainWorkOrderRequest request);

    SoloChainWorkOrderResponse update(SoloChainWorkOrderRequest request);

    SoloChainWorkOrderResponse delete(SoloChainWorkOrderRequest request);

    UpdateErpWorkOrderResponse update(UpdateErpWorkOrderRequest request);
}
