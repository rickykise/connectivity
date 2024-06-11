package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

public enum EUOIStatus {
    ACTIVE("Active"), CANCELLED("Cancelled");

    private final String displayName;

    EUOIStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
