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

import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType.PLUS_DAY_0;

@Getter
@RequiredArgsConstructor
public enum CenterArrivalType {
    NORMAL("1", "정상 도착"),
    DELAY("2", "지연 도착"),
    EARLY("3", "조기 도착");

    private final String erpCode;
    private final String description;

    private static final Map<String, CenterArrivalType> typeMapByErpCode =
            Arrays.stream(CenterArrivalType.values())
                    .collect(Collectors.toMap(CenterArrivalType::getErpCode, Function.identity()));

    public static CenterArrivalType findByErpCode(String erpType) {
        CenterArrivalType type = typeMapByErpCode.get(erpType);
        if (type == null) {
            throw new NoSuchEnumElementException(CenterArrivalType.class.getSimpleName(), erpType);
        }
        return type;
    }

    public static CenterArrivalType findTypeWhenArrival(InOrder inOrder, LocalDateTime centerArrivalDateTime) {
        final LocalDateTime ORDER_DATE_TIME = LocalDateTime.of(inOrder.orderDate(), LocalTime.of(0, 0));
        final LocalDateTime ORDER_DATE_TIME_PLUS_13_HOURS = ORDER_DATE_TIME.plusHours(13);
        final LocalDateTime ORDER_DATE_TIME_PLUS_16_HOURS = ORDER_DATE_TIME.plusHours(16);

        if (PLUS_DAY_0 == inOrder.releaseType()) {
            if (centerArrivalDateTime.isBefore(ORDER_DATE_TIME)) { //(조기도착)
                return EARLY;
            } else if (centerArrivalDateTime.isBefore(ORDER_DATE_TIME_PLUS_13_HOURS)) { //(정상도착)
                return NORMAL;
            } else { //(지연도착)
                //당일출고 지연시 익일 출고로 변환후 16시 이전 도착이면 정상 처리
                return centerArrivalDateTime.isBefore(ORDER_DATE_TIME_PLUS_16_HOURS) ? NORMAL : DELAY;
            }
        } else { //익일출고, D+2일 출고
            if (centerArrivalDateTime.isBefore(ORDER_DATE_TIME)) { //(조기도착)
                return EARLY;
            } else {
                return centerArrivalDateTime.isBefore(ORDER_DATE_TIME_PLUS_16_HOURS) ? NORMAL : DELAY;
            }
        }
    }
}
