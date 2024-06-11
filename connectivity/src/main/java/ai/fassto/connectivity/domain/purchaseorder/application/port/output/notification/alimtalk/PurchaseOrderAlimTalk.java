package ai.fassto.connectivity.domain.purchaseorder.application.port.output.notification.alimtalk;

import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;

public interface PurchaseOrderAlimTalk {
    /**
     * 입고검수, 반품검수
     */
    //입고|반품 Damaged 상품 고객확인 알림톡 전송
    void sendClientConfirmTalk(PurchaseOrder purchaseOrder);

    /**
     * 센터도착
     */
    //1.센터도착시 알림톡 전송
    void sendCenArrivalTalk(PurchaseOrder purchaseOrder);

    //2.익일출고 -> 당일출고 변환 알림톡 전송
    void sendConvertAdviceTalk(PurchaseOrder purchaseOrder);

    //3.도착시간 지연되는 경우, 고객에게 알림톡 전송
    void sendDelayArrivalTalk(PurchaseOrder purchaseOrder);

    //입고완료 알림톡 전송
    void sendInCompleteTalk(PurchaseOrder purchaseOrder);

}
