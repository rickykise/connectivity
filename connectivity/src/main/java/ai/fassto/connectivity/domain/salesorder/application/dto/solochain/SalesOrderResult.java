package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

import lombok.Builder;

@Builder
public record SalesOrderResult(
        String slipNo,
        String ordNo,
        String result
) {
}
