package ai.fassto.connectivity.domain.workorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class WorkOrderExternalApiException extends ExternalApiCallException {


    public WorkOrderExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public WorkOrderExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public WorkOrderExternalApiException(Throwable cause) {
        super(cause);
    }

    public WorkOrderExternalApiException(String message) {
        super(message);
    }

    public WorkOrderExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
