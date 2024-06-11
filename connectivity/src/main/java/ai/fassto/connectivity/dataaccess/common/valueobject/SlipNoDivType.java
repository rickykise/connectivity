package ai.fassto.connectivity.dataaccess.common.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 전표번호(slipNo) 생성시 div(구분) 필요한 구분 값
 */
@Getter
@RequiredArgsConstructor
public enum SlipNoDivType {

    IN_CHECK("ICH","입고검수"),
    IN("I","입고확정"),
    OUT("O","출고확정"),
    TALK_DREAM("TDRM", "톡드림 알림톡");

    private final String erpCode;
    private final String description;
}
