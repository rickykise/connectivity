package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseOrderItemNotFoundException extends PurchaseOrderDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();

    public PurchaseOrderItemNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public PurchaseOrderItemNotFoundException(String message) {
        super(message);
    }

    public PurchaseOrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
