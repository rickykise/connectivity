package ai.fassto.connectivity.domain.item.core.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DistributionTerm { // 유통기간 관련
    private boolean managed; // 유통기간 관리 여부
    private Integer daysBeforeSendingAlarmForDistributionTerm; // 유통기간 임박 알람 기준
    private Integer daysBeforeOutProhibited; // 출고 금지일 기준


}
