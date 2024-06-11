package ai.fassto.connectivity.domain.common.valueobject.enums;

public enum EWeightUnit {
    GRAM("g"), K_GRAM("kg");

    private final String displayName;

    EWeightUnit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
