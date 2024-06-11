package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum ReleaseType {
    PLUS_DAY_0("0", "당일(D+0) 출고"),
    PLUS_DAY_1("1", "익일(D+1) 출고"),
    PLUS_DAY_2("2", "정상(D+2) 출고");

    @Getter
    private final String erpCode;
    private final String description;

    private static final Map<String, ReleaseType> typeMapByErpCode =
            Arrays.stream(ReleaseType.values())
                    .collect(Collectors.toMap(ReleaseType::getErpCode, Function.identity()));

    public static ReleaseType findByErpCode(String erpType) {
        ReleaseType type = typeMapByErpCode.get(erpType);
        if (type == null) {
            throw new NoSuchEnumElementException(ReleaseType.class.getSimpleName(), erpType);
        }
        return type;
    }

    public static ReleaseType findTypeWhenArrival(InOrder inOrder, LocalDateTime centerArrivalDateTime) {
        final LocalDateTime ORDER_DATE_TIME = LocalDateTime.of(inOrder.orderDate(), LocalTime.of(0, 0));
        final LocalDateTime ORDER_DATE_TIME_PLUS_13_HOURS = ORDER_DATE_TIME.plusHours(13);

        if (PLUS_DAY_0 == inOrder.releaseType()) {
            if (centerArrivalDateTime.isEqual(ORDER_DATE_TIME_PLUS_13_HOURS) || centerArrivalDateTime.isAfter(ORDER_DATE_TIME_PLUS_13_HOURS)) {
                return PLUS_DAY_1;
            }
        }

        return inOrder.releaseType();
    }
}
