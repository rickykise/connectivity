package ai.fassto.connectivity.domain.parcel.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class TmsParcelExternalApiException extends ExternalApiCallException {


    public TmsParcelExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public TmsParcelExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public TmsParcelExternalApiException(Throwable cause) {
        super(cause);
    }

    public TmsParcelExternalApiException(String message) {
        super(message);
    }

    public TmsParcelExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
