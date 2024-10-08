package ai.fassto.connectivity.application.common.exception.handler;

import ai.fassto.connectivity.application.common.dto.ApiErrorResponse;
import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.common.exception.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.*;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.common.exception.ExceptionUtil.getResponseEntityBy;

@Slf4j
@RestControllerAdvice
@Order()
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        return getResponseEntityBy(request, INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @ExceptionHandler(value = {PropertyNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleException(PropertyNotFoundException exception, HttpServletRequest request) {
        log.error("CONNECTIVITY PROPERTY NOT FOUND EXCEPTION: {}", toJson(exception.getErrorList()));

        return getResponseEntityBy(request, INTERNAL_SERVER_ERROR_MESSAGE);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ApiErrorResponse> handleBindException(BindException exception, HttpServletRequest request) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ErrorDetail> errors = new ArrayList<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(new ErrorDetail(error.getField(), error.getDefaultMessage()));
        }

        return getResponseEntityBy(request, PARAMETER_ERROR_MESSAGE, errors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiErrorResponse> handleValidationException(ConstraintViolationException exception, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<ErrorDetail> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : violations) {
            errors.add(new ErrorDetail(ExceptionUtil.getFieldFromConstraintViolation(violation), violation.getMessage()));
        }

        return getResponseEntityBy(request, PARAMETER_ERROR_MESSAGE, errors);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, InvalidFormatException.class})
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(InvalidFormatException exception, HttpServletRequest request) {
        List<ErrorDetail> errors = new ArrayList<>();
        if (exception.getPath() == null || exception.getPath().get(0) == null || exception.getPath().get(0).getFieldName() == null || exception.getMessage() == null) {
            log.error("", exception);
        } else {
            errors.add(new ErrorDetail(exception.getPath().get(0).getFieldName(), exception.getMessage()));
        }

        return getResponseEntityBy(request, PARAMETER_ERROR_MESSAGE, errors);
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<ApiErrorResponse> handleDuplicateKeyException(DuplicateKeyException exception, HttpServletRequest request) {
        log.error("[SQL]DuplicateKey: ", exception);

        List<ErrorDetail> errors = new ArrayList<>();
        errors.add(new ErrorDetail("Duplicate Key", "Duplicate entry for key 'PRIMARY"));

        return getResponseEntityBy(request, DUPLICATE_KEY_ERROR_MESSAGE, errors);
    }

    @ExceptionHandler(value = {NoSuchEnumElementException.class})
    public ResponseEntity<ApiErrorResponse> handleNoSuchEnumElementException(NoSuchEnumElementException exception, HttpServletRequest request) {
        log.error("[Enum]NoSuchEnumElement: {}", toJson(exception.getErrorList()), exception);

        return getResponseEntityBy(request, NO_SUCH_ENUM_ELEMENT_ERROR_MESSAGE, exception.getErrorList());
    }

    @ExceptionHandler(value = {NotBlankException.class})
    public ResponseEntity<ApiErrorResponse> handleNotBlankException(NotBlankException exception, HttpServletRequest request) {
        log.error("[ERROR]NotBlank: {}", toJson(exception.getErrorList()), exception);

        return getResponseEntityBy(request, NOT_BLANK_ERROR_MESSAGE, exception.getErrorList());
    }

    @ExceptionHandler(value = {NotNullException.class})
    public ResponseEntity<ApiErrorResponse> handleNotNullException(NotNullException exception, HttpServletRequest request) {
        log.error("[ERROR]NotNull: {}", toJson(exception.getErrorList()), exception);

        return getResponseEntityBy(request, NOT_NULL_ERROR_MESSAGE, exception.getErrorList());
    }

    @ExceptionHandler(value = {PositiveValueException.class})
    public ResponseEntity<ApiErrorResponse> handlePositiveValueException(PositiveValueException exception, HttpServletRequest request) {
        log.error("[ERROR]PositiveValue: {}", toJson(exception.getErrorList()), exception);

        return getResponseEntityBy(request, POSITIVE_ERROR_MESSAGE, exception.getErrorList());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiErrorResponse> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        log.error("HttpRequestMethodNotSupportedException: url: {}, message: {}", request.getServletPath(), toJson(exception.getMessage()));

        return getResponseEntityBy(request, HTTP_METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(value = {SizeLimitException.class})
    public ResponseEntity<ApiErrorResponse> handleSizeLimitException(SizeLimitException exception, HttpServletRequest request) {
        log.error("[ERROR]SizeLimitException: {}", toJson(exception.getErrorList()), exception);

        return getResponseEntityBy(request, SIZE_LIMIT_ERROR_MESSAGE, exception.getErrorList());
    }

}
