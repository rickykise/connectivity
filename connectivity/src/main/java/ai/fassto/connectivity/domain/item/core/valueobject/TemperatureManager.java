package ai.fassto.connectivity.domain.item.core.valueobject;

import lombok.Builder;

@Builder
public record TemperatureManager(
        String code,
        String name,
        String minimumTemperature,
        String maximumTemperature
) {
}
