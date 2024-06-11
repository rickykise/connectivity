package ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PositiveGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class OrderLine {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Code (코드)", example = "00953CHG-000001")
    private String code;
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Sequence (시퀀스)", example = "1")
    private Integer sequence;

    /**
     * 원래 주문 수량
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Order Quantity (주문 수량)", example = "300")
    private Integer orderQty;
    /**
     * 해당 주문 라인의 "품목" 섹션에서 확인된 모든 기본 수량의 합계와 같습니다. 주문 수량 이상일 수 있습니다(수신 초과).
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Received Quantity (수신 수량)", example = "300")
    private Integer receivedQty;
    /**
     * 잔여수량 = 주문수량 - 수신수량. 아직 검수되지 않은 예상 잔량입니다
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Remaining Quantity (잔여 수량)", example = "0")
    private Integer remainingQty;
    /**
     * 관리자가 주문을 마감할 때 남은 수량입니다(고객이 일부 수량을 미납한 경우).
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Cancelled Quantity (미납 수량)", example = "0")
    private Integer cancelledQty;

    public void validate() {
        code = StringUtils.trim(code);
    }

}
