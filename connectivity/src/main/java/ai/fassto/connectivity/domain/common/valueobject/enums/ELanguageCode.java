package ai.fassto.connectivity.domain.common.valueobject.enums;

public enum ELanguageCode {
    KOREAN("ko"), ENGLISH("en");

    private final String code;

    ELanguageCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
