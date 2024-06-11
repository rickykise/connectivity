package ai.fassto.connectivity.domain.salesorder.application.dto.erp.update;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import ai.fassto.connectivity.domain.common.exception.NotNullException;
import ai.fassto.connectivity.domain.common.exception.PositiveValueException;
import ai.fassto.connectivity.domain.salesorder.application.dto.Box;
import ai.fassto.connectivity.domain.salesorder.application.dto.Consumable;
import ai.fassto.connectivity.domain.salesorder.application.dto.VehicleInfo;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus.PACKED;
import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus.SHIPPED;

@Getter
@Setter
@NoArgsConstructor
public class UpdateErpSalesOrderRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip Number (출고요청번호)", example = "TESTIO221109007")
    private String slipNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "orderClass should be SALES", example = "SALES")
    private String orderClass;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^(SalesOrder|SalesOrderSingleSKU|ManualExpiry|CarryOutOrder|RUSH)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
    @ApiModelProperty(required = true, notes = "Order Type (주문 종류 - SalesOrder: 출고, SalesOrderSingleSKU: item과 lot 이 하나일때 출고, ManualExpiry: 유통기한 지정 출고, CarryOutOrder:반출, RUSH: 긴급)", example = "SalesOrder")
    private String orderType;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Order Status (주문 상태) - ALLOCATED:출고지시확정, PICKED:피킹, PACKED:패킹, SHIPPED: 출고완료, OUTSTANDING:출고지시확정 취소", example = "PACKED")
    private String orderStatus;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    LocalDateTime workDate;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String warehouseCode;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer code (고객사 코드)", example = "01123")
    private String customerCode;

    @Valid
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "OrderLines")
    private List<OrderLine> orderLines;

    @Valid
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(notes = "Sales Item list (출고상품 리스트)")
    private List<Item> items;

    @Valid
    private List<VehicleInfo> vehicleInfos;

    @Valid
    private List<Consumable> consumables;

    @Valid
    private List<Box> boxes;

    public UpdateErpSalesOrderRequest validate() {
        slipNo = StringUtils.trim(slipNo);
        orderClass = StringUtils.trim(orderClass);
        orderType = StringUtils.trim(orderType);
        orderStatus = StringUtils.trim(orderStatus);
        warehouseCode = StringUtils.trim(warehouseCode);
        customerCode = StringUtils.trim(customerCode);

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
        if (!CollectionUtils.isEmpty(vehicleInfos)) {
            for (VehicleInfo vehicleInfo : vehicleInfos) {
                vehicleInfo.validate();
            }
        }

        if (!CollectionUtils.isEmpty(consumables)) {
            for (Consumable consumable : consumables) {
                consumable.validate();
            }
        }

        if (!CollectionUtils.isEmpty(boxes)) {
            for (Box box : boxes) {
                box.validate(orderStatus);
            }
        }

        if (PACKED.is(orderStatus) || SHIPPED.is(orderStatus)) {
            if (boxes == null) {
                throw new NotNullException("boxes", String.format("When OrderStatus is \"%s\", boxes cannot be null", orderStatus));
            }
            if (boxes.size() < 1) {
                throw new PositiveValueException("boxes", String.format("When OrderStatus is \"%s\", boxes count must be greater than 0", orderStatus));
            }
        }

        return this;
    }
}
