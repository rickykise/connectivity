package ai.fassto.connectivity.application.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.scheduler.core.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.*;
import static ai.fassto.connectivity.domain.common.exception.ExceptionUtil.getResponseEntityBy;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SchedulerExceptionHandler {

    @ExceptionHandler(value = {SchedulerNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> schedulerNotFoundExceptionHandler(SchedulerNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SCHEDULER_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SchedulerStatusNotRegisterException.class})
    public ResponseEntity<ApiErrorResponse> schedulerStatusNotRegisterExceptionHandler(SchedulerStatusNotRegisterException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SCHEDULER_STATUS_NOT_REGISTERD_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SchedulerUnableRegisterException.class})
    public ResponseEntity<ApiErrorResponse> schedulerUnableRegisterExceptionHandler(SchedulerUnableRegisterException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SCHEDULER_UNABLE_REGISTER_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

}
