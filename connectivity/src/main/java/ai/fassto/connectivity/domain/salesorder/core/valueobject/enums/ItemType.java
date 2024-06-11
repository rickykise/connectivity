package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ItemType {
    SINGLE("1", "단품상품"),
    COLLECTION("2", "모음상품"),
    BUNDLE("3", "묶음상품");

    private final String erpCode;
    private final String description;

    private static final Map<String, ItemType> typeMapByErpCode =
            Arrays.stream(ItemType.values())
                    .collect(Collectors.toMap(ItemType::getErpCode, Function.identity()));

    public static ItemType findByErpCode(String erpCode) {
        ItemType type = typeMapByErpCode.get(erpCode);
        if (type == null) {
            throw new NoSuchEnumElementException(ItemType.class.getSimpleName(), erpCode);
        }
        return type;
    }
}
