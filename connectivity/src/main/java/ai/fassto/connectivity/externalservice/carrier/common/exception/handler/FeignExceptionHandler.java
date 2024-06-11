package ai.fassto.connectivity.externalservice.carrier.common.exception.handler;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.parcel.core.exception.YlpParcelExternalApiException;
import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationErrorResponse;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import ai.fassto.connectivity.common.utility.MapperUtil;
import ai.fassto.connectivity.domain.parcel.core.exception.TmsParcelExternalApiException;
import ai.fassto.connectivity.externalservice.carrier.tms.dto.TmsErrorResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Message;
import feign.FeignException;
import org.apache.http.HttpStatus;

import java.util.List;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.*;

public class FeignExceptionHandler {
    public static void tmsExternalApiCallExceptionHandler(List<ErrorDetail> errorList) throws ExternalApiCallException {
        throw new ExternalApiCallException(PARCEL_EXTERNAL_API_CALL_ERROR_MESSAGE, errorList);
    }

    public static void tmsExternalApiCallExceptionHandler(FeignException exception) throws ExternalApiCallException {
        if (exception.status() == HttpStatus.SC_UNAUTHORIZED) {
            throw new ExternalApiCallException(PARCEL_UNAUTHORIZED_ERROR_MESSAGE, exception);
        } else if (exception.status() == HttpStatus.SC_BAD_REQUEST ||
                exception.status() == HttpStatus.SC_NOT_FOUND
        ) {
            var errorResponse = MapperUtil.getGsonForArgument().fromJson(exception.contentUTF8(), TmsErrorResponse.class);

            throw new TmsParcelExternalApiException(
                    CARRIER_TMS_EXTERNAL_API_CALL_ERROR_MESSAGE,
                    List.of(new ErrorDetail(errorResponse.getErrorCode(), errorResponse.getErrorMessage()))
            );
        }

        throw new ExternalApiCallException(PARCEL_EXTERNAL_API_CALL_ERROR_MESSAGE, exception);
    }

    public static void ylpExternalApiCallExceptionHandler(FeignException exception) throws ExternalApiCallException {
        if (exception.status() == HttpStatus.SC_UNAUTHORIZED) {
            throw new ExternalApiCallException(PARCEL_UNAUTHORIZED_ERROR_MESSAGE, exception);
        } else if (exception.status() == HttpStatus.SC_BAD_REQUEST ||
                exception.status() == HttpStatus.SC_NOT_FOUND
        ) {
            var errorResponse = MapperUtil.getGsonForArgument().fromJson(exception.contentUTF8(), VehicleAllocationErrorResponse.class);

            throw new YlpParcelExternalApiException(
                    CARRIER_YLP_EXTERNAL_API_CALL_ERROR_MESSAGE,
                    List.of(new ErrorDetail(errorResponse.getErrorCode(), errorResponse.getErrorMessage()))
            );
        }

        throw new ExternalApiCallException(PARCEL_EXTERNAL_API_CALL_ERROR_MESSAGE, exception);
    }

    public static void carrierTmsExceptionHandler(List<Message> messages) throws TmsParcelExternalApiException {
        throw new TmsParcelExternalApiException(
                CARRIER_TMS_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void omsExternalApiCallExceptionHandler(FeignException exception) throws ExternalApiCallException {
        throw new ExternalApiCallException(OMS_EXTERNAL_API_CALL_ERROR_MESSAGE, exception);
    }

    private static List<ErrorDetail> getErrorList(List<Message> messages) {
        return messages.stream().map(message -> new ErrorDetail(message.id(), message.message())).toList();
    }
}
