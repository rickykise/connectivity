package ai.fassto.connectivity.domain.purchaseorder.core.valueobject;

import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.CenterArrivalType;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.VehicleType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record InOrder(
        PurchaseOrderId purchaseOrderId,
        WarehouseId warehouseId,
        CustomerId customerId,
        VendorId vendorId,
        ItemId itemId,
        LocalDate orderDate, // 지시일자
        LocalDate makeDate, // 제조일자
        LocalDate releasableDate, // 출고 가능일
        ReleaseType releaseType,
        VehicleType vehicleType,
        String centerArrivalDate,
        String centerArrivalTime,
        CenterArrivalType centerArrivalType
) {
}
