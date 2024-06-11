package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderItemDisabledException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderItemDisabledException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderItemDisabledException(String message) {
        super(message);
    }

    public SalesOrderItemDisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
