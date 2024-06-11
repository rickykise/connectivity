package ai.fassto.connectivity.domain.parcel.application.valueobject;

import lombok.Builder;

@Builder
public record AllocationInfo(
        String warehouseCode,
        String code,
        String arrangeNo
) {
}
