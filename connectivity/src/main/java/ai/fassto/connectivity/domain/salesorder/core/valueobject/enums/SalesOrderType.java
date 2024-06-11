package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ai.fassto.connectivity.domain.common.valueobject.enums.OrderType.RETURN;
import static ai.fassto.connectivity.domain.common.valueobject.enums.OrderType.SALES;

@Getter
@RequiredArgsConstructor
public enum SalesOrderType {
    /**
     * SalesOrder: Sales Class. Fassto ships to a customer center or a consumer's house. 2+ SKUs
     * SalesOrder-SingleSKU: Sales Class. Fassto ships to a customer center or a consumer's house. Only 1 SKU
     * CarryOutOrder: Return Class. A customer wants Fassto return some inventory. Fassto ships it back to the vendor
     * ManualExpiry : Sales Class. Fassto use this order type when the customer select a specific expiry date in the ERP.
     * RUSH : Sales Class. Fassto ships to a customer center or a consumer's house. Urgent
     */
    SALES_ORDER("SalesOrder", "출고", SALES),
    SALES_ORDER_SINGLE_SKU("SalesOrderSingleSKU", "Single-SKU 기준: item 과 lot 이 1한 개인경우", SALES), // 출고 상품이 한개 한 박스인 경우 출고에 이점이 있음
    MANUAL_EXPIRY("ManualExpiry", "유통기한 지정 출고", SALES),
    CARRY_OUT_ORDER("CarryOutOrder", "반출", RETURN),
    RUSH("RUSH", "긴급출고", SALES);

    private final String type;
    private final String description;
    private final OrderType orderType;

    private static final Map<String, SalesOrderType> typeMapByType =
            Arrays.stream(SalesOrderType.values())
                    .collect(Collectors.toMap(SalesOrderType::getType, Function.identity()));

    public static SalesOrderType findByType(String type) {
        SalesOrderType t = typeMapByType.get(type);
        if (t == null) {
            throw new NoSuchEnumElementException(SalesOrderType.class.getSimpleName(), type);
        }
        return t;
    }

    public static boolean isSalesOrder(String type) {
        return SALES_ORDER.getType().equalsIgnoreCase(type);
    }

}
