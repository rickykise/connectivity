package ai.fassto.connectivity.domain.common.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class PropertyNotFoundException extends RuntimeException {
    private final List<ErrorDetail> errorList;


    public PropertyNotFoundException(String field, String value) {
        this.errorList = List.of(new ErrorDetail(field, value));
    }
}
