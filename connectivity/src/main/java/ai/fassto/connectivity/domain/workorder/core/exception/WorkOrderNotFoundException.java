package ai.fassto.connectivity.domain.workorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WorkOrderNotFoundException extends WorkOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public WorkOrderNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public WorkOrderNotFoundException(String message) {
        super(message);
    }

    public WorkOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
