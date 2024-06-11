package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ai.fassto.connectivity.domain.common.valueobject.enums.OrderType.RETURN;
import static ai.fassto.connectivity.domain.common.valueobject.enums.OrderType.SALES;

@Getter
@AllArgsConstructor
public enum PurchaseOrderType {
    PURCHASE_ORDER("PurchaseOrder", SALES, "입고"),
    CONSUMER_RETURN_ORDER("ConsumerReturnOrder", RETURN, "고객의 요청으로 고객사에서 ERP를 통해서 반품 신청하는 경우"),
    CENTER_RETURN_ORDER("CenterReturnOrder", RETURN, "고객이 택배사를 통해서 반품을 신청해서 바로 센터로 도착한 경우 반품 처리");

    private final String type;

    private final OrderType orderType;
    private final String description;

    private static final Map<String, PurchaseOrderType> typeMapByType =
            Arrays.stream(PurchaseOrderType.values())
                    .collect(Collectors.toMap(PurchaseOrderType::getType, Function.identity()));

    public static PurchaseOrderType findByType(String type) {
        PurchaseOrderType t = typeMapByType.get(type);
        if (t == null) {
            throw new NoSuchEnumElementException(PurchaseOrderType.class.getSimpleName(), type);
        }
        return t;
    }

    public static boolean isPurchaseOrderBy(String type) {
        return PURCHASE_ORDER.getType().equalsIgnoreCase(type);
    }


}
