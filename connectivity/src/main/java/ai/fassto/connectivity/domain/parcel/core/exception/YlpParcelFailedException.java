package ai.fassto.connectivity.domain.parcel.core.exception;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class YlpParcelFailedException extends ParcelDomainException{
    private List<ErrorDetail> errorList = new ArrayList<>();

    public YlpParcelFailedException(ErrorDetail errorDetail) {
        this.errorList.add(errorDetail);
    }

    public YlpParcelFailedException(String message) {
        super(message);
    }

    public YlpParcelFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
