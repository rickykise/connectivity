package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoloChainSalesOrdersRequest {
    @Valid
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @ApiModelProperty(required = true, notes = "Sales Orders")
    List<SoloChainSalesOrderRequest> salesOrders;
    public SoloChainSalesOrdersRequest validate() {
        for (SoloChainSalesOrderRequest salesOrder : salesOrders) {
            salesOrder.validate();
        }

        return this;
    }
}
