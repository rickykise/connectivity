package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.domain.item.core.exception.ItemExternalApiException;
import ai.fassto.connectivity.domain.item.core.exception.ItemNotFoundException;
import ai.fassto.connectivity.domain.item.core.exception.ItemNotUsedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.ITEM_NOT_FOUND_ERROR_MESSAGE;
import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.ITEM_NOT_USED_ERROR_MESSAGE;
import static ai.fassto.connectivity.domain.common.exception.ExceptionUtil.getResponseEntityBy;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ItemExceptionHandler {

    @ExceptionHandler(value = {ItemExternalApiException.class})
    public ResponseEntity<ApiErrorResponse> itemExternalApiExceptionHandler(ItemExternalApiException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, exception.getExceptionMessage(), exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {ItemNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> itemNotFoundExceptionHandler(ItemNotFoundException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, ITEM_NOT_FOUND_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

    @ExceptionHandler(value = {ItemNotUsedException.class})
    public ResponseEntity<ApiErrorResponse> itemNotUsedExceptionHandler(ItemNotUsedException exception, HttpServletRequest request) {
        ResponseEntity<ApiErrorResponse> response = getResponseEntityBy(request, ITEM_NOT_USED_ERROR_MESSAGE, exception.getErrorList());
        log.error("{}", toJson(response));
        return response;
    }

}
