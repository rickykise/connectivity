package ai.fassto.connectivity.externalservice.common.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import lombok.Getter;

import java.util.List;

@Getter
public class ExternalApiCallException extends RuntimeException {
    private ExceptionMessages exceptionMessage;
    private List<ErrorDetail> errorList;

    public ExternalApiCallException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(cause);
        this.exceptionMessage = exceptionMessage;
    }

    public ExternalApiCallException(ExceptionMessages exceptionMessage) {
        super("");
        this.exceptionMessage = exceptionMessage;
    }

    public ExternalApiCallException(ExceptionMessages exceptionMessage, List<ErrorDetail> detailList) {
        this.exceptionMessage = exceptionMessage;
        this.errorList = detailList;
    }

    public ExternalApiCallException(String message) {
        super(message);
    }

    public ExternalApiCallException(Throwable cause) {
        super(cause);
    }

    public ExternalApiCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
