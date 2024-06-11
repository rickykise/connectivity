package ai.fassto.connectivity.domain.parcel.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

import java.util.List;

public class ParcelDomainException extends DomainException {
    public ParcelDomainException() {
    }

    public ParcelDomainException(String message) {
        super(message);
    }

    public ParcelDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
