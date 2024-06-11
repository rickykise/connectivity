package ai.fassto.connectivity.domain.purchaseorder.application.port.output.external.api.erp;

import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;

public interface PurchaseOrderErp {
    void sendClientConfirmTalk(PurchaseOrder purchaseOrder);
}
