package ai.fassto.connectivity.domain.item.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

public class ItemDomainException extends DomainException {
    public ItemDomainException() {
    }

    public ItemDomainException(String message) {
        super(message);
    }

    public ItemDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
