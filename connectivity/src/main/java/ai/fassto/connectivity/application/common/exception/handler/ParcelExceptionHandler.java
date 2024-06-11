package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.parcel.core.exception.TmsParcelExternalApiException;
import ai.fassto.connectivity.domain.parcel.core.exception.VehicleParcelExternalApiException;
import ai.fassto.connectivity.domain.parcel.core.exception.YlpParcelFailedException;
import ai.fassto.connectivity.domain.parcel.core.exception.YlpParcelExternalApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.CARRIER_YLP_FAILED_ERROR_MESSAGE;
import static ai.fassto.connectivity.domain.common.exception.ExceptionUtil.getResponseEntityBy;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ParcelExceptionHandler {

    @ExceptionHandler(value = {TmsParcelExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> carrierTmsExternalApiExceptionHandler(TmsParcelExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));

        return response;
    }

    @ExceptionHandler(value = {VehicleParcelExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> carrierVehicleExternalApiExceptionHandler(VehicleParcelExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));

        return response;
    }

    @ExceptionHandler(value = {YlpParcelExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> carrierYlpExternalApiExceptionHandler(YlpParcelExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));

        return response;
    }

    @ExceptionHandler(value = {YlpParcelFailedException.class})
    public ResponseEntity<ApiErrorResponse> carrierYlpParcelFailedApiExceptionHandler(YlpParcelFailedException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, CARRIER_YLP_FAILED_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));

        return response;
    }
}
