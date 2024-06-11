package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseOrderStatus {
    STANDBY("PO 생성 및 작업 수락 요청 중"),
    /**
     * 이 단계에서 가능한 상태값은 다음과 같습니다.
     * 도착, 활성, 완료 (ARRIVED, ACTIVE, COMPLETED)
     * <p>
     * PO에 존재하는 다른 상태는 다음과 같습니다.
     * STDBY, CANCELLED
     */
    CANCELLED("취소"),
    ARRIVED("센터 도착"),
    ACTIVE("입고 검수중"),
    COMPLETED("입고 완료");

    private final String description;


    public static PurchaseOrderStatus findBy(String name) {
        PurchaseOrderStatus status = null;
        try {
            status = PurchaseOrderStatus.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new NoSuchEnumElementException(PurchaseOrderStatus.class.getSimpleName(), name);
        }

        return status;
    }

}
