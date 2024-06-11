package ai.fassto.connectivity.domain.workorder.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WorkOrderNotSupportException extends WorkOrderDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();

    public WorkOrderNotSupportException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public WorkOrderNotSupportException(String message) {
        super(message);
    }

    public WorkOrderNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

}
