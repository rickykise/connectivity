package ai.fassto.connectivity.domain.scheduler.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SchedulerStatusNotRegisterException extends SchedulerDomainException {

    private List<ErrorDetail> errorList = new ArrayList<>();

    public SchedulerStatusNotRegisterException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public SchedulerStatusNotRegisterException(String message) {
        super(message);
    }

    public SchedulerStatusNotRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

}
