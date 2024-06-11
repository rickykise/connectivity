package ai.fassto.connectivity.domain.purchaseorder.application.port.input.service;


import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.PatchSoloChainPurchaseOrderQaOnHoldItemsRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.PatchSoloChainPurchaseOrderQaOnHoldItemsResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse;

public interface PurchaseOrderApplicationService {

    SoloChainPurchaseOrderResponse create(SoloChainPurchaseOrderRequest request);

    SoloChainPurchaseOrderResponse update(SoloChainPurchaseOrderRequest request);

    SoloChainPurchaseOrderResponse delete(SoloChainPurchaseOrderRequest request);

    UpdateErpPurchaseOrderResponse update(UpdateErpPurchaseOrderRequest request);

    PatchSoloChainPurchaseOrderQaOnHoldItemsResponse confirm(PatchSoloChainPurchaseOrderQaOnHoldItemsRequest request);
}
