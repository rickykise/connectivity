package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

public enum SalesOrderStatus {
    /**
     * 출고에서는 STDBY 상태가 존재하지 않음 - 주문이 들어오면 작업을 한다고 생각하면 됨     *
     * ACTIVE: 활성화 상태
     * CANCELLED: 주문취소
     */
    ACTIVE, CANCELLED;
}
