package ai.fassto.connectivity.domain.purchaseorder.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;

public interface PurchaseOrderWms {
    PurchaseOrder create(PurchaseOrder purchaseOrder);

    PurchaseOrder update(PurchaseOrder purchaseOrder);

    PurchaseOrder delete(PurchaseOrder purchaseOrder);
}
