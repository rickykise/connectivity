package ai.fassto.connectivity.domain.workorder.application.dto.erp.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class OrderLine {
    private String code;
    private String sequence;

    /**
     * 원래 주문 수량
     */
    private Integer orderQty;
    /**
     * 해당 주문 라인의 "품목" 섹션에서 확인된 모든 기본 수량의 합계와 같습니다. 주문 수량 이상일 수 있습니다(수신 초과).
     */
    private Integer completedQty;
    /**
     * 잔여수량 = 주문수량 - 수신수량. 아직 검수되지 않은 예상 잔량입니다
     */
    private Integer remainingQty;
    /**
     * 관리자가 주문을 마감할 때 남은 수량입니다(고객이 일부 수량을 미납한 경우).
     */
    private Integer cancelledQty;

    public void validate() {
        code = StringUtils.trim(code);
        sequence = StringUtils.trim(sequence);
    }
}
