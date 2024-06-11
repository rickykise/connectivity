package ai.fassto.connectivity.dataaccess.common.valueobject;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum InventoryCondition {
    SERVICEABLE("0001", "SERVICEABLE"),
    UNSERVICEABLE("0002", "UNSERVICEABLE"),
    QA_ON_HOLD("0003", "QAONHOLD");

    private final String erpCode;
    private final String solochainCode;

    private static final Map<String, InventoryCondition> codeMapBySolochainCode =
            Arrays.stream(InventoryCondition.values())
                    .collect(Collectors.toMap(InventoryCondition::getSolochainCode, Function.identity()));

    public static InventoryCondition findBySolochainCode(String solochainCode) {
        InventoryCondition s = codeMapBySolochainCode.get(solochainCode);
        if (s == null) {
            throw new NoSuchEnumElementException(InventoryCondition.class.getSimpleName(), solochainCode);
        }
        return s;
    }

}
