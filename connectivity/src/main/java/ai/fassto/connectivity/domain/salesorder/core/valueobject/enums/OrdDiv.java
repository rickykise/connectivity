package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrdDiv {
    GENERAL("N","일반주문"),
    COUPANG("C","쿠팡쉽먼트"),
    ONE_DAY("D","원데이배송(체인로지스)"),
    THE_DAY("T","당일입출고"),
    ROSES_VALLEY("E","당일배송(로지스밸리)"),
    DAWN("F","새벽배송"),
    CORPORATION("G","기업택배");

    private final String erpCode;
    private final String description;

}
