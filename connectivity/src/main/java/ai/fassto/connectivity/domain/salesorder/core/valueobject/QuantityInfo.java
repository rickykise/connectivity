package ai.fassto.connectivity.domain.salesorder.core.valueobject;


import lombok.Builder;

@Builder
public record QuantityInfo(
        String godDiv, // (상품분류(상품:god, 추가상품:addGod))
        /**
         * 원래 주문 수량
         */
        Integer orderQuantity,

        /**
         * 할당 수량
         */
        Integer allocatedQuantity,

        /**
         * 피킹 수량
         */
        Integer pickQuantity,

        /**
         * 패킹 수량
         */
        Integer packQuantity,

        /**
         * 출고 수량
         */
        Integer shippedQty,

        /**
         * 취소 수량
         */
        Integer cancelledQty
) {
    static boolean isGoodsBy(String godDiv) {
        return "god".equalsIgnoreCase(godDiv);
    }
}
