package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderAllocateCancelledException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderAllocateCancelledException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderAllocateCancelledException(String message) {
        super(message);
    }

    public SalesOrderAllocateCancelledException(String message, Throwable cause) {
        super(message, cause);
    }
}
