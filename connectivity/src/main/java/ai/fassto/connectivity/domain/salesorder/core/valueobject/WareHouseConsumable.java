package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import lombok.Builder;

@Builder
public record WareHouseConsumable(
        int index,
        String size,
        String type,
        String useYn
) {
}
