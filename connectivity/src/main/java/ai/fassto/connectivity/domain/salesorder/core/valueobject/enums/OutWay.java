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
public enum OutWay {
    FIRST_IN_FIRST_OUT("1", "선입선출"),
    LAST_IN_FIRST_OUT("2", "후입선출"),
    EXPIRATION_DATE("3", "유통기간 지정");

    private static final Map<String, OutWay> wayMapByErpCode =
            Arrays.stream(OutWay.values())
                    .collect(Collectors.toMap(OutWay::getErpCode, Function.identity()));

    private final String erpCode;
    private final String description;

    public static OutWay findByErpCode(String erpCode) {
        OutWay outWay = wayMapByErpCode.get(erpCode);
        if (outWay == null) {
            throw new NoSuchEnumElementException(OutWay.class.getSimpleName(), erpCode);
        }
        return outWay;
    }


}
