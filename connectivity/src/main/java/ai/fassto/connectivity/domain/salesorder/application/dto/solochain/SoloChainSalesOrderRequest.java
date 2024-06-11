package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoloChainSalesOrderRequest {
    @Valid
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Sales Order Data")
    private OrderData orderData;

    @Valid
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @ApiModelProperty(required = true, notes = "Sales Order list (출고신청 리스트)")
    private List<Goods> godList;

    @Valid
    @ApiModelProperty(notes = "COUPANG File List(쿠팡파일 리스트)")
    private List<CoupangFile> coupangFileList;


    public SoloChainSalesOrderRequest validate() {
        orderData.validate();
        
        for (Goods goods : godList) {
            goods.validate();
        }

        if (!CollectionUtils.isEmpty(coupangFileList)) {
            for (CoupangFile coupangFile : coupangFileList) {
                coupangFile.validate();
            }
        }

        return this;
    }
}
