package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class SalesOrderLotMisMatchException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderLotMisMatchException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderLotMisMatchException(String message) {
        super(message);
    }

    public SalesOrderLotMisMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
