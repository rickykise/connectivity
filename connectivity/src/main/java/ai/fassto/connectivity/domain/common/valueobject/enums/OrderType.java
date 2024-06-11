package ai.fassto.connectivity.domain.common.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum OrderType {
    SALES("1", "Sales", "입고 at PO, 출고 at SO"),
    RETURN("2", "Return", "반품 at PO, 반출 at SO");

    private final String erpCode;
    private final String solochainType;
    private final String description;

    private static final Map<String, OrderType> erpTypeMapByType =
            Arrays.stream(OrderType.values())
                    .collect(Collectors.toMap(OrderType::getErpCode, Function.identity()));

    public static OrderType findByType(String type) {
        OrderType t = erpTypeMapByType.get(type);
        if (t == null) {
            throw new NoSuchEnumElementException(OrderType.class.getSimpleName(), type);
        }
        return t;
    }

}
