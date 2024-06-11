package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseOrderNotCenterArrivalException extends PurchaseOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public PurchaseOrderNotCenterArrivalException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public PurchaseOrderNotCenterArrivalException(String message) {
        super(message);
    }

    public PurchaseOrderNotCenterArrivalException(String message, Throwable cause) {
        super(message, cause);
    }
}
