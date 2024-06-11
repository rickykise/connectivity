package ai.fassto.connectivity.domain.purchaseorder.application.valueobject;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum EPurchaseOrderStatus {
    Arrive, // : 센터도착
    Wait, // : 검수 보류
    Check, // : 검수 완료
    Complete, // : 입고 완료
    Cancel // : 입고 취소 (ERP에서 전달)
}
