package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

public class PurchaseOrderDomainException extends DomainException {
    public PurchaseOrderDomainException() {
    }

    public PurchaseOrderDomainException(String message) {
        super(message);
    }

    public PurchaseOrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
