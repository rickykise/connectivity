package ai.fassto.connectivity.domain.salesorder.application.dto.erp.update;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PositiveGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
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
    @ApiModelProperty(required = true, notes = "Quantity (주문 수량)", example = "21")
    private Integer quantity;

    /**
     * 할당 수량
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Allocated Quantity (할당 수량)", example = "20")
    private Integer allocatedQty;

    /**
     * 피킹 수량
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Picked Quantity (피킹 수량)", example = "1")
    private Integer pickedQty;

    /**
     * 패킹 수량
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Packed Quantity (패킹 수량)", example = "1")
    private Integer packQty;

    /**
     * 출고 수량
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Shipped Quantity (출고 수량)", example = "1")
    private Integer shippedQty;

    /**
     * 취소 수량
     */
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @PositiveOrZero(message = "{ai.fassto.connectivity.constraints.PositiveOrZero.message}", groups = PositiveGroup.class)
    @ApiModelProperty(required = true, notes = "Cancelled Quantity (취소 수량)", example = "1")
    private Integer cancelledQty;

    public void validate() {
        code = StringUtils.trim(code);
    }
}
