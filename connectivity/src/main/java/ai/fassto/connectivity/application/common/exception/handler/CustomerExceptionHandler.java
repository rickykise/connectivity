package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.customer.core.exception.CustomerExternalApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.domain.common.exception.ExceptionUtil.getResponseEntityBy;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerExceptionHandler {

    @ExceptionHandler(value = {CustomerExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> customerExternalApiExceptionHandler(CustomerExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));

        return response;
    }
}
