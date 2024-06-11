package ai.fassto.connectivity.domain.common.exception;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.application.common.valueobject.ExceptionMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public class ExceptionUtil {
    public static ResponseEntity<ApiErrorResponse> getResponseEntityBy(HttpServletRequest request, ExceptionMessages message) {
        return getResponseEntityBy(request, message, null);
    }

    public static ResponseEntity<ApiErrorResponse> getResponseEntityBy(HttpServletRequest request, ExceptionMessages message, List<ErrorDetail> errors) {
        if (isAcceptLanguageKorean(request)) {
            return new ResponseEntity<>(
                    getApiErrorResponseBy(request, message.getMessageKo(), message.getOriginalStatus(), errors)
                    , message.getReturnStatusForOnlyKorean()
            );
        }

        return new ResponseEntity<>(
                getApiErrorResponseBy(request, message.getMessage(), message.getOriginalStatus(), errors)
                , message.getOriginalStatus()
        );
    }

    public static String getFieldFromConstraintViolation(ConstraintViolation<?> violation) {
        String path = violation.getPropertyPath().toString();
        String[] split = path.split("\\.");
        return split[split.length - 1];
    }

    private static ApiErrorResponse getApiErrorResponseBy(HttpServletRequest request, String message, HttpStatus httpStatus, List<ErrorDetail> errors) {
        return ApiErrorResponse.builder()
                .status(httpStatus.value())
                .statusReason(httpStatus.getReasonPhrase())
                .message(message)
                .url(request.getServletPath())
                .errorAt(LocalDateTime.now())
                .errors(errors)
                .build();
    }
}
