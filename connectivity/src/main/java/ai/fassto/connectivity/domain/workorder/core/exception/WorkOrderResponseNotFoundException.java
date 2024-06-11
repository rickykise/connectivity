package ai.fassto.connectivity.domain.workorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WorkOrderResponseNotFoundException extends WorkOrderDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public WorkOrderResponseNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public WorkOrderResponseNotFoundException(String message) {
        super(message);
    }

    public WorkOrderResponseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
