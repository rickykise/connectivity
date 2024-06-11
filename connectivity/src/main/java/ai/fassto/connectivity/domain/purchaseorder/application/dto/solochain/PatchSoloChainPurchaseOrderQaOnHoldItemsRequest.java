package ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class PatchSoloChainPurchaseOrderQaOnHoldItemsRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip Number (입고요청번호)", example = "DT012212120003123")
    private String slipNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})

    @ApiModelProperty(required = true, notes = "Receipt Number (영수증 번호)", example = "22220391")
    private String receiptNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})

    @ApiModelProperty(required = true, notes = "status (가용/ 불가용 상태)", example = "SERVICEABLE")
    private String status;

    public PatchSoloChainPurchaseOrderQaOnHoldItemsRequest validate() {
        slipNo = StringUtils.trim(slipNo);
        receiptNo = StringUtils.trim(receiptNo);
        status = StringUtils.trim(status);

        return this;
    }
}


