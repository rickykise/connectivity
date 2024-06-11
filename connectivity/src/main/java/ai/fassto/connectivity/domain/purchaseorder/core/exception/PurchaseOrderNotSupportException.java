package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseOrderNotSupportException extends PurchaseOrderDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();

    public PurchaseOrderNotSupportException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public PurchaseOrderNotSupportException(String message) {
        super(message);
    }

    public PurchaseOrderNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
