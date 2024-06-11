package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.domain.common.exception.ExceptionUtil.getResponseEntityBy;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
public class ExternalApiExceptionHandler {

    @ExceptionHandler(value = {ExternalApiCallException.class})
    public ResponseEntity<ApiErrorResponse> handleException(ExternalApiCallException exception, HttpServletRequest request) {
        log.error("", exception);

        return getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
    }
}
