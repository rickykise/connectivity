package ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.PurchaseOrderRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class SoloChainPurchaseOrderRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^(PurchaseOrder|ConsumerReturnOrder|CenterReturnOrder)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
    @ApiModelProperty(required = true, notes = "order type (입고신청 종류: PurchaseOrder, ConsumerReturnOrder, CenterReturnOrder)", example = "PurchaseOrder")
    private String orderType;

    @Valid
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @ApiModelProperty(required = true, notes = "Purchase Order list (입고신청 리스트)")
    private List<PurchaseOrderRequest> dataList;

    public SoloChainPurchaseOrderRequest validate() {
        orderType = StringUtils.trim(orderType);
        int index = 0;

        for (PurchaseOrderRequest purchaseOrderRequest : dataList) {
            purchaseOrderRequest.validate(orderType, ++index);
        }

        return this;
    }
}
