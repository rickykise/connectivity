package ai.fassto.connectivity.domain.parcel.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.Getter;

import java.util.List;

@Getter
public class VehicleParcelExternalApiException extends ExternalApiCallException {

    public VehicleParcelExternalApiException(ExceptionMessages exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public VehicleParcelExternalApiException(ExceptionMessages exceptionMessage, List<ErrorDetail> errorList) {
        super(exceptionMessage, errorList);
    }

    public VehicleParcelExternalApiException(Throwable cause) {
        super(cause);
    }

    public VehicleParcelExternalApiException(String message) {
        super(message);
    }

    public VehicleParcelExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
