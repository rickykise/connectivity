package ai.fassto.connectivity.domain.common.valueobject.enums;

public enum EMeasureUnit {
    CM("cm");

    private final String displayName;

    EMeasureUnit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
