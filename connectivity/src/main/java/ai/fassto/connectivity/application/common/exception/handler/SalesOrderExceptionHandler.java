package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.salesorder.core.exception.*;
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
public class SalesOrderExceptionHandler {

    @ExceptionHandler(value = {SalesOrderExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderExternalApiExceptionHandler(SalesOrderExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderNotFoundExceptionHandler(SalesOrderNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_REQUEST_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderPickMapNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderPickMapNotFoundExceptionHandler(SalesOrderPickMapNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_PICK_MAP_REQUEST_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderAllocateCancelledException.class})
    public ResponseEntity<ApiErrorResponse> SalesOrderAllocateCancelledExceptionHandler(SalesOrderAllocateCancelledException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_ALLOCATE_CANCELLED_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }


    @ExceptionHandler(value = {SalesOrderNotSupportException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderNotSupportExceptionHandler(SalesOrderNotSupportException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_NOT_SUPPORT_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderItemNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderItemNotFoundExceptionHandler(SalesOrderItemNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_ITEM_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderItemDisabledException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderItemDisabledExceptionHandler(SalesOrderItemDisabledException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_ITEM_DISABLED_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderLotMisMatchException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderLotMisMatchExceptionHandler(SalesOrderLotMisMatchException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_LOT_MIS_MATCH_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {SalesOrderWareHouseConsumableNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> salesOrderWareHouseConsumableNotFoundExceptionHandler(SalesOrderWareHouseConsumableNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, SALES_ORDER_WAREHOUSE_CONSUMABLE_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

}
