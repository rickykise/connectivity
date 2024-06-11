package ai.fassto.connectivity.domain.stock.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

public class StockDomainException extends DomainException {
    public StockDomainException(String message) {
        super(message);
    }

    public StockDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
