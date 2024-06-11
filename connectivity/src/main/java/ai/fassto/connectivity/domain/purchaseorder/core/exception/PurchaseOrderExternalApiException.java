package ai.fassto.connectivity.domain.purchaseorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class PurchaseOrderExternalApiException extends ExternalApiCallException {


    public PurchaseOrderExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public PurchaseOrderExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public PurchaseOrderExternalApiException(Throwable cause) {
        super(cause);
    }

    public PurchaseOrderExternalApiException(String message) {
        super(message);
    }

    public PurchaseOrderExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
