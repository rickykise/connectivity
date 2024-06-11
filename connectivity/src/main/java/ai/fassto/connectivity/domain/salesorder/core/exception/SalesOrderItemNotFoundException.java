package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class SalesOrderItemNotFoundException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderItemNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderItemNotFoundException(String message) {
        super(message);
    }

    public SalesOrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
