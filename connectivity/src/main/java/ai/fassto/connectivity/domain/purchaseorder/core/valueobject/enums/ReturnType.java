package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ReturnType {
    RETURN("01", "return", "반품"),
    EXCHANGE("02", "exchange", "교환"),
    REFUND("03", "refund", "환불");

    private final String erpCode;
    private final String solochainName;
    private final String description;

    private static final Map<String, ReturnType> typeMapByErpCode =
            Arrays.stream(ReturnType.values())
                    .collect(Collectors.toMap(ReturnType::getErpCode, Function.identity()));

    public static ReturnType findByErpCode(String erpCode) {
        ReturnType type = typeMapByErpCode.get(erpCode);
        if (type == null) {
            throw new NoSuchEnumElementException(ReturnType.class.getSimpleName(), erpCode);
        }
        return type;
    }

}
