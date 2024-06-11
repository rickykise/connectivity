package ai.fassto.connectivity.domain.item.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemExternalApiException extends ExternalApiCallException {

    public ItemExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public ItemExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public ItemExternalApiException(Throwable cause) {
        super(cause);
    }

    public ItemExternalApiException(String message) {
        super(message);
    }

    public ItemExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
