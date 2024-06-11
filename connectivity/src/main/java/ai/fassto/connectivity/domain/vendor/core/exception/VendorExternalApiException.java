package ai.fassto.connectivity.domain.vendor.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class VendorExternalApiException extends ExternalApiCallException {


    public VendorExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public VendorExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public VendorExternalApiException(Throwable cause) {
        super(cause);
    }

    public VendorExternalApiException(String message) {
        super(message);
    }

    public VendorExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
