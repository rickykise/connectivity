package ai.fassto.connectivity.domain.purchaseorder.core.valueobject;

import lombok.Builder;

@Builder
public record VehicleInfo(
        String trackingNo, // 송장번호 : "FEDEX1234567",
        String vehicleType, // 01: 택배(Courier), 02: 차량(Vehicle)
        String vehicleTypeName, // courier, vehicle
        String originalCarrier,
        String originalTrackingNumber,
        String returnReason,
        String returnReasonName,
        String inboundTransportCompany,
        String inboundLicensePlate,
        String driverContact,
        String memo
) {
}

