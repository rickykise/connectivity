package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum ItemCondition {
    SERVICEABLE("01", "SERVICEABLE"),
    UNSERVICEABLE("02", "UNSERVICEABLE"),
    QA_ON_HOLD("03", "QAONHOLD");

    private static final Map<String, ItemCondition> typeMapBySolochainCode =
            Arrays.stream(ItemCondition.values())
                    .collect(Collectors.toMap(ItemCondition::getSolochainCode, Function.identity()));
    private final String erpRtnGodStatusCode;
    private final String solochainCode;

    public static String getErpRtnGodStatusCodeBy(String solochainCode) {
        ItemCondition type = typeMapBySolochainCode.get(solochainCode);
        if (type == null) {
            return QA_ON_HOLD.getErpRtnGodStatusCode();
        }
        return type.getErpRtnGodStatusCode();
    }


}
