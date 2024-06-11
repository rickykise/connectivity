package ai.fassto.connectivity.domain.common.valueobject.enums;

public enum ActionType {
    CREATE, UPDATE, DELETE;

    public boolean isDelete() {
        return DELETE.equals(this);
    }


}
