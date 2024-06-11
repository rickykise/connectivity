package ai.fassto.connectivity.domain.scheduler.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SchedulerNotFoundException extends SchedulerDomainException {

    private List<ErrorDetail> errorList = new ArrayList<>();
    public SchedulerNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SchedulerNotFoundException(String message) {
        super(message);
    }

    public SchedulerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
