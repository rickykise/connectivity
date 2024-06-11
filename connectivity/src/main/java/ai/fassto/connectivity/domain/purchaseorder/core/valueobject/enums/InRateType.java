package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.CenterArrivalInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.CenterArrivalType.*;
import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType.*;


/**
 * 입고할증률 코드 rateGbn 확인 (INP18)
 * CenterArrivalType/ReleaseType : +-x% (rateGbn)
 * 당일출고/지연도착 : +150% (R01)
 * 당일출고/정상도착 : +100% (R02)
 * 당일출고/조기도착 : +150% (R03)
 * 익일출고/지연도착 : +50% (R11)
 * 익일출고/정상도착 : 0% (R12)
 * 익일출고/조기도착 : +50% (R13)
 * D+2출고/지연도착 : +0% (R21)
 * D+2출고/정상도착 : -50% (R22)
 * D+2출고/조기도착 : +0% (R23)
 **/
@Getter
@RequiredArgsConstructor
public enum InRateType {
    RELEASE_PLUS_DAY_0_WHEN_DELAYED_ARRIVAL("R01", PLUS_DAY_0, DELAY, "당일출고/지연도착 : +150% (R01)"),
    RELEASE_PLUS_DAY_0_WHEN_ARRIVAL("R02", PLUS_DAY_0, NORMAL, "당일출고/정상도착 : +100% (R02)"),
    RELEASE_PLUS_DAY_0_WHEN_EARLY_ARRIVAL("R03", PLUS_DAY_0, EARLY, "당일출고/조기도착 : +150% (R03)"),
    RELEASE_PLUS_DAY_1_WHEN_DELAYED_ARRIVAL("R11", PLUS_DAY_1, DELAY, "익일출고/지연도착 : +50% (R11)"),
    RELEASE_PLUS_DAY_1_WHEN_ARRIVAL("R12", PLUS_DAY_1, NORMAL, "익일출고/정상도착 : 0% (R12)"),
    RELEASE_PLUS_DAY_1_WHEN_EARLY_ARRIVAL("R13", PLUS_DAY_1, EARLY, "익일출고/조기도착 : +50% (R13)"),
    RELEASE_PLUS_DAY_2_WHEN_DELAYED_ARRIVAL("R21", PLUS_DAY_2, DELAY, "D+2출고/지연도착 : 0% (R21)"),
    RELEASE_PLUS_DAY_2_WHEN_ARRIVAL("R22", PLUS_DAY_2, NORMAL, "D+2출고/정상도착 : -50% (R22)"),
    RELEASE_PLUS_DAY_2_WHEN_EARLY_ARRIVAL("R23", PLUS_DAY_2, EARLY, "D+2출고/조기도착 : 0% (R23)");

    private final String erpCode;
    private final ReleaseType releaseType;
    private final CenterArrivalType centerArrivalType;
    private final String description;

    public static InRateType findBy(CenterArrivalInfo centerArrivalInfo) {
        ReleaseType releaseType = centerArrivalInfo.releaseType();
        CenterArrivalType centerArrivalType = centerArrivalInfo.centerArrivalType();

        InRateType inRateType = Arrays.stream(values())
                                        .filter(v -> v.releaseType == releaseType && v.centerArrivalType == centerArrivalType)
                                        .findFirst()
                                        .orElseThrow(() -> new NoSuchEnumElementException(
                                                        InRateType.class.getSimpleName(),
                                                        String.format("releaseType: %s, centerArrivalType: %s", releaseType.getErpCode(), centerArrivalType.getErpCode())
                                                )
                                        );
        return inRateType;
    }

}
