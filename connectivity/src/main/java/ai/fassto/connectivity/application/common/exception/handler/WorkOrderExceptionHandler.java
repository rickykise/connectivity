package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderExternalApiException;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderNotFoundException;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderNotSupportException;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderResponseNotFoundException;
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
public class WorkOrderExceptionHandler {

    @ExceptionHandler(value = {WorkOrderExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> workOrderExternalApiExceptionHandler(WorkOrderExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }


    @ExceptionHandler(value = {WorkOrderResponseNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> workOrderResponseNotFoundExceptionHandler(WorkOrderResponseNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, WORK_ORDER_RESPONSE_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {WorkOrderNotSupportException.class})
    public ResponseEntity<ApiErrorResponse> workOrderResponseNotSupportExceptionHandler(WorkOrderNotSupportException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, WORK_ORDER_NOT_SUPPORT_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {WorkOrderNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> workOrderNotFoundExceptionHandler(WorkOrderNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, WORK_ORDER_REQUEST_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }



}
