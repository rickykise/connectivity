package ai.fassto.connectivity.domain.item.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemNotFoundException extends ItemDomainException {
    private List<ErrorDetail> errorList = new ArrayList<>();

    public ItemNotFoundException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
