package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

public enum ESiteType {
    CUSTOMER("Customer"),
    VENDOR("Vendor"),
    ITEM("Item");

    private final String solochainName;

    ESiteType(String solochainName) {
        this.solochainName = solochainName;
    }

    public String getSolochainName() {
        return solochainName;
    }
}
