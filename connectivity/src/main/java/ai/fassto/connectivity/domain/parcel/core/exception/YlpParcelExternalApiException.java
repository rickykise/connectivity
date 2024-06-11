package ai.fassto.connectivity.domain.parcel.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class YlpParcelExternalApiException extends ExternalApiCallException {

    public YlpParcelExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public YlpParcelExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public YlpParcelExternalApiException(Throwable cause) {
        super(cause);
    }

    public YlpParcelExternalApiException(String message) {
        super(message);
    }

    public YlpParcelExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
