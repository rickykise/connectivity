package ai.fassto.connectivity.domain.item.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemNotUsedException extends ItemDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public ItemNotUsedException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public ItemNotUsedException(String message) {
        super(message);
    }

    public ItemNotUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}
