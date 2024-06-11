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
public enum OutDiv {
    COURIER("1", "택배"),
    VEHICLE("2", "차량/기타"),
    OVERSEAS("3", "해외"),
    OVERSEAS_ECR("4", "해외(이크레모스)"),
    EXPRESS("5", "EXPRESS(UPS)"),
    STANDARD("6", "Standard(한진특송)"),
    E_COMMERCE_All("7", "Standard(이커머스올)"),
    VISIT_RECEIVE("8", "방문수령")
    ;

    private static final Map<String, OutDiv> divMapByErpCode =
            Arrays.stream(OutDiv.values())
                    .collect(Collectors.toMap(OutDiv::getErpCode, Function.identity()));

    private final String erpCode;
    private final String description;

    public static OutDiv findByErpCode(String erpCode) {
        OutDiv outDiv = divMapByErpCode.get(erpCode);
        if (outDiv == null) {
            throw new NoSuchEnumElementException(OutDiv.class.getSimpleName(), erpCode);
        }
        return outDiv;
    }
}
