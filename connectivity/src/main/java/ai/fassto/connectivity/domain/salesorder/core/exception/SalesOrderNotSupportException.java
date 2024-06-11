package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderNotSupportException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderNotSupportException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderNotSupportException(String message) {
        super(message);
    }

    public SalesOrderNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
