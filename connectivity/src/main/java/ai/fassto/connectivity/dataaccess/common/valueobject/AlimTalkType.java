package ai.fassto.connectivity.dataaccess.common.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlimTalkType {

    CEN_ARRIVED("10282","[센터도착]"),
    PLUS_DAY_0_DELAY("store_0007","[센터도착] 당일출고 지연 (13시 이후 도착)"),        //사용안함
    PLUS_DAY_2_DELAY("store_0008","[센터도착] 익일,D+2 출고 지연 (16시 이후 도착)"),   //사용안함
    PLUS_DAY_1_TO_DAY_0("store_0009","[센터도착] 익일 출고건이 00~13 시 도착 시, 당일 출고 변환 권유"),
    IN_CLIENT_CONFIRM("erp_001", "[입고검수] 고객확인"),
    RETURN_CLIENT_CONFIRM("erp_002", "[반품검수] 고객확인"),
    IN_COMPLETED("10283","[입고완료]"),
    RETURN_COMPLETED("ALT_CST_CSM_RTN_02","[반품완료]")
    ;

    private final String code;
    private final String status;


}
