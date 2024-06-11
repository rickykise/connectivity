package ai.fassto.connectivity.domain.purchaseorder.core.valueobject;

import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.CenterArrivalType;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType;
import lombok.Builder;

import java.time.LocalDateTime;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_HHmm;
import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyyMMdd;

@Builder
public record CenterArrivalInfo(
        LocalDateTime centerArrivalDateTime, //센터도착일 세팅, 센터도착시간 세팅
        CenterArrivalType centerArrivalType, //센터도착유형(정상도착, 지연도착, 조기도착) 세팅
        ReleaseType releaseType //출고유형(당일출고, 익일출고, 정상출고) 세팅
) {
    public String getArrivalDateAsString() { // 2022-11-10 11:07:23.001
        return centerArrivalDateTime.format(DATE_FORMATTER_yyyyMMdd); // 센터도착일자 -> 20221110
    }

    public String getArrivalTimeAsString() { // 2022-11-10 11:07:23.001
        return centerArrivalDateTime.format(DATE_FORMATTER_HHmm);   // 센터도착시간 -> 1107
    }
}
