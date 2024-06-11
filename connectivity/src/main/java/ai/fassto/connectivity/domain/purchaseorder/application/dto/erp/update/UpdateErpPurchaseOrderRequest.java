package ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update;

import ai.fassto.connectivity.domain.common.exception.NotNullException;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class UpdateErpPurchaseOrderRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip Number (입고요청번호)", example = "TESTIO221109007")
    private String slipNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "orderClass should be SALES", example = "SALES")
    private String orderClass;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^(PurchaseOrder|ConsumerReturnOrder|CenterReturnOrder)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
    @ApiModelProperty(required = true, notes = "Order Type (주문 종류)", example = "PurchaseOrder")
    private String orderType;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^(ARRIVED|ACTIVE|COMPLETED)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
    @ApiModelProperty(required = true, notes = "Order Status (주문 상태)", example = "ARRIVED")
    private String orderStatus;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime receivedDate;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String warehouseCode;

    @Valid
    @ApiModelProperty(notes = "CheckList info in the PDA (작업자용 PDA 체크리스트)")
    private CustomHeaderCheckListInfo customHeaderCheckListInfo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer code (고객사 코드)", example = "10915")
    private String customerCode;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Worker Count (작업자 수)", example = "5")
    private Integer workerQty; // : 1,

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "WorkTimeInMinutes (작업시간 (분))", example = "30")
    private Integer workTime; // : 30,

    @Valid
    @ApiModelProperty(required = true, notes = "OrderLines")
    private List<OrderLine> orderLines;

    @Valid
    @ApiModelProperty(notes = "items")
    private List<Item> items;

    public UpdateErpPurchaseOrderRequest validate() {
        slipNo = StringUtils.trim(slipNo);
        orderClass = StringUtils.trim(orderClass);
        orderType = StringUtils.trim(orderType);
        orderStatus = StringUtils.trim(orderStatus);
        warehouseCode = StringUtils.trim(warehouseCode);
        customerCode = StringUtils.trim(customerCode);

        if ("COMPLETED".equals(orderStatus)) {
            if (customHeaderCheckListInfo == null) {
                throw new NotNullException("customHeaderCheckListInfo", null);
            }
            customHeaderCheckListInfo.validate(orderType);
        }

        if (!CollectionUtils.isEmpty(orderLines)) {
            for (OrderLine orderLine : orderLines) {
                orderLine.validate();
            }
        }

        if (!CollectionUtils.isEmpty(items)) {
            for (Item item : items) {
                item.validate(orderStatus);
            }
        }

        return this;
    }
}
