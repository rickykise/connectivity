package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.*;
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
public class PurchaseOrderExceptionHandler {

    @ExceptionHandler(value = {PurchaseOrderExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> purchaseOrderExternalApiExceptionHandler(PurchaseOrderExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {PurchaseOrderNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> purchaseOrderNotFoundExceptionHandler(PurchaseOrderNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, PURCHASE_ORDER_REQUEST_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {PurchaseOrderItemNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> purchaseOrderItemNotFoundExceptionHandler(PurchaseOrderItemNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, PURCHASE_ORDER_ITEM_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {PurchaseOrderItemDisabledException.class})
    public ResponseEntity<ApiErrorResponse> purchaseOrderItemDisabledExceptionHandler(PurchaseOrderItemDisabledException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, PURCHASE_ORDER_ITEM_DISABLED_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {PurchaseOrderNotSupportException.class})
    public ResponseEntity<ApiErrorResponse> purchaseOrderNotSupportExceptionHandler(PurchaseOrderNotSupportException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, PURCHASE_ORDER_NOT_SUPPORT_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {PurchaseOrderNotCenterArrivalException.class})
    public ResponseEntity<ApiErrorResponse> purchaseOrderNotCenterArrivalExceptionHandler(PurchaseOrderNotCenterArrivalException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, PURCHASE_ORDER_NOT_CENTER_ARRIVAL_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }
}
