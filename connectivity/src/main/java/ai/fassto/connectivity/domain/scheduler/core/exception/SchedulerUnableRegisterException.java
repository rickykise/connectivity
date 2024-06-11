package ai.fassto.connectivity.domain.scheduler.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SchedulerUnableRegisterException extends SchedulerDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();
    public SchedulerUnableRegisterException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SchedulerUnableRegisterException(String message) {
        super(message);
    }

    public SchedulerUnableRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
