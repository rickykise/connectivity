package ai.fassto.connectivity.domain.purchaseorder.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.PatchSoloChainPurchaseOrderQaOnHoldItemsRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.PatchSoloChainPurchaseOrderQaOnHoldItemsResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.port.input.service.PurchaseOrderApplicationService;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderNotSupportException;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseOrderApplicationServiceImpl implements PurchaseOrderApplicationService {
    private final PurchaseOrderCreateRequestHandler purchaseOrderCreateRequestHandler;
    private final PurchaseOrderUpdateRequestHandler purchaseOrderUpdateRequestHandler;
    private final PurchaseOrderDeleteRequestHandler purchaseOrderDeleteRequestHandler;

    @Override
    public SoloChainPurchaseOrderResponse create(SoloChainPurchaseOrderRequest request) {
        return purchaseOrderCreateRequestHandler.createPurchaseOrder(request);
    }

    @Override
    public SoloChainPurchaseOrderResponse update(SoloChainPurchaseOrderRequest request) {
        return purchaseOrderUpdateRequestHandler.updatePurchaseOrder(request);
    }

    @Override
    public SoloChainPurchaseOrderResponse delete(SoloChainPurchaseOrderRequest request) {
        return purchaseOrderDeleteRequestHandler.deletePurchaseOrder(request);
    }

    @Override
    public UpdateErpPurchaseOrderResponse update(UpdateErpPurchaseOrderRequest request) {
        PurchaseOrderStatus status = findBy(request.getOrderStatus());
        if (status.equals(ARRIVED)) {
            return purchaseOrderUpdateRequestHandler.arriveAtCenter(request);
        } else if (status.equals(ACTIVE)) {
            return purchaseOrderUpdateRequestHandler.activate(request);
        } else if (status.equals(COMPLETED)) {
            return purchaseOrderUpdateRequestHandler.complete(request);
        }/* else if (status.equals(CANCELLED)) {
            return purchaseOrderUpdateRequestHandler.cancel(request);
        }*/ else {
            throw new PurchaseOrderNotSupportException(new ErrorDetail("PurchaseOrderStatus", status.name()));
        }
    }

    @Override
    public PatchSoloChainPurchaseOrderQaOnHoldItemsResponse confirm(PatchSoloChainPurchaseOrderQaOnHoldItemsRequest request) {
        return new PatchSoloChainPurchaseOrderQaOnHoldItemsResponse(request.getSlipNo(), request.getReceiptNo(), request.getStatus());
    }
}
