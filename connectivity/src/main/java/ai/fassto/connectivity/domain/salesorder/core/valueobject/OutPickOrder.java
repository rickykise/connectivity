package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import ai.fassto.connectivity.domain.common.valueobject.id.SalesOrderId;
import lombok.Builder;

@Builder
public record OutPickOrder(
        SalesOrderId salesOrderId,
        String workStatus
) {
}
