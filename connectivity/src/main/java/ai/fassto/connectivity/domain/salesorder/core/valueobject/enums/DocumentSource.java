package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentSource {
    COUPANG("upload/coupang");

    private final String s3Path;
}
