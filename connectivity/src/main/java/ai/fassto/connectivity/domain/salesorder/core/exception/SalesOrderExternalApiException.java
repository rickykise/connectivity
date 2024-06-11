package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class SalesOrderExternalApiException extends ExternalApiCallException {


    public SalesOrderExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public SalesOrderExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public SalesOrderExternalApiException(Throwable cause) {
        super(cause);
    }

    public SalesOrderExternalApiException(String message) {
        super(message);
    }

    public SalesOrderExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
