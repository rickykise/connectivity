package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseOrderNotFoundException extends PurchaseOrderDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();

    public PurchaseOrderNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }

    public PurchaseOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
