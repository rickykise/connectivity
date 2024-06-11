package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.valueobject;

public enum PurchaseOrderStatus {
    /**
     * STDBY: 고객사에서 PURCHASE ORDER를 넣으면 - 창고 작업자가 주문을 받아들이기 전까지 상태
     * ACTIVE: 창고 작업자가 주문을 받아들인 이후 상태
     * CANCELLED: 주문취소
     */
    STDBY, ACTIVE, CANCELLED
}
