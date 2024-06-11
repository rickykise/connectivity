package ai.fassto.connectivity.domain.customer.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomerExternalApiException extends ExternalApiCallException {


    public CustomerExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public CustomerExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public CustomerExternalApiException(Throwable cause) {
        super(cause);
    }

    public CustomerExternalApiException(String message) {
        super(message);
    }

    public CustomerExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
