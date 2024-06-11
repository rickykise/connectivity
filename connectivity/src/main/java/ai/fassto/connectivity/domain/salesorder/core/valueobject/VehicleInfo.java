package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.VehicleType;
import lombok.Builder;

@Builder
public record VehicleInfo(
        /** type 이 택배(courier) 인 경우 */
        String trackingNo, // 송장번호 : "FEDEX1234567",
        String companyCode,
        VehicleType type, // 택배(Courier), 차량(Vehicle)
        String shippingServiceLevelCode,
        String shippingServiceLevelName

) {
}

