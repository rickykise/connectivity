package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import lombok.Builder;

@Builder
public record Result(
        String result,
        SalesOrder salesOrder
) {
}
