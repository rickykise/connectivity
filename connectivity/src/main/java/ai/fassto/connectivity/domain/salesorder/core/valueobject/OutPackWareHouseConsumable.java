package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import lombok.Builder;

@Builder
public record OutPackWareHouseConsumable(
        int wareHouseConsumableIndex,
        Integer qty
) {
}
