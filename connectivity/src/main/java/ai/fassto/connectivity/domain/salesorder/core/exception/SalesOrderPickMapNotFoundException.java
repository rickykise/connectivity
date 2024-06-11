package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderPickMapNotFoundException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderPickMapNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderPickMapNotFoundException(String message) {
        super(message);
    }

    public SalesOrderPickMapNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
