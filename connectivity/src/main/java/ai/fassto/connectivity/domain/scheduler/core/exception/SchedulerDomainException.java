package ai.fassto.connectivity.domain.scheduler.core.exception;

import ai.fassto.connectivity.domain.common.exception.DomainException;

public class SchedulerDomainException extends DomainException {

    public SchedulerDomainException() {

    }

    public SchedulerDomainException(String message) {
        super(message);
    }

    public SchedulerDomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
