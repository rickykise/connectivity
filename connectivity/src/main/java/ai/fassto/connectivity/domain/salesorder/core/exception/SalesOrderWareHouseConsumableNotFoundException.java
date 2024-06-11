package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderWareHouseConsumableNotFoundException extends SalesOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public SalesOrderWareHouseConsumableNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SalesOrderWareHouseConsumableNotFoundException(String message) {
        super(message);
    }

    public SalesOrderWareHouseConsumableNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
