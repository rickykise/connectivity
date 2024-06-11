package ai.fassto.connectivity.domain.salesorder.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

public class SalesOrderDomainException extends DomainException {
    public SalesOrderDomainException() {
    }

    public SalesOrderDomainException(String message) {
        super(message);
    }

    public SalesOrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
