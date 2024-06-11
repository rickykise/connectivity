package ai.fassto.connectivity.domain.parcel.core.valueobject;

import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.id.SalesOrderId;
import ai.fassto.connectivity.domain.common.valueobject.id.ShopId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OutOrder(
        SalesOrderId salesOrderId,
        WarehouseId warehouseId,
        CustomerId customerId,
        ShopId shopId,
        String outDiv,
        String orderNo,
        Integer outTp,
        Integer orderSequence,
        LocalDate orderDate  //지시일자
) {
}
