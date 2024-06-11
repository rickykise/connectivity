package ai.fassto.connectivity.domain.workorder.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

public class WorkOrderDomainException extends DomainException {
    public WorkOrderDomainException() {}

    public WorkOrderDomainException(String message) {
        super(message);
    }

    public WorkOrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
