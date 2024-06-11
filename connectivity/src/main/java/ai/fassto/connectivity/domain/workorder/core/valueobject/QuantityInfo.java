package ai.fassto.connectivity.domain.workorder.core.valueobject;

import lombok.Builder;

@Builder
public record QuantityInfo(
        /**
         * 원래 주문 수량
         */
        Integer orderQuantity,
        /**
         * 완료수량
         */
        Integer completedQuantity,
        /**
         * 잔여수량 = 주문수량 - 수신수량. 아직 검수되지 않은 예상 잔량입니다
         */
        Integer remainingQuantity,
        /**
         * 관리자가 주문을 마감할 때 남은 수량입니다(고객이 일부 수량을 미납한 경우).
         */
        Integer cancelledQuantity
) {
}
