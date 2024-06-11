package ai.fassto.connectivity.domain.workorder.core.valueobject;

import lombok.Builder;

@Builder
public record QuantityInfo_(
        Integer orderQuantity,
        Integer consumedQuantity,
        Integer remainingQuantity,
        Integer cancelledQuantity
) {

}
