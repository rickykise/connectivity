package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderNotFoundException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderNotFoundException(String message) {
        super(message);
    }

    public SalesOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
