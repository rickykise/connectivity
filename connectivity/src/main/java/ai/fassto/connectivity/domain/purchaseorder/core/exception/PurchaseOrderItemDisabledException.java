package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseOrderItemDisabledException extends PurchaseOrderDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();

    public PurchaseOrderItemDisabledException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public PurchaseOrderItemDisabledException(String message) {
        super(message);
    }

    public PurchaseOrderItemDisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
