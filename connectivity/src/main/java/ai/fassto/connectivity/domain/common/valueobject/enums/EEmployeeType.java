package ai.fassto.connectivity.domain.common.valueobject.enums;

public enum EEmployeeType {
    MASTER(0), SUB1(1), SUB2(2);

    private final Integer no;

    EEmployeeType(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }
}
