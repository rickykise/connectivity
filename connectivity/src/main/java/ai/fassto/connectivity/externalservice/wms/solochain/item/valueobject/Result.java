package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import ai.fassto.connectivity.domain.item.core.entity.Item;
import lombok.Builder;

@Builder
public record Result(
        String result,
        String godCd,
        String godNm
) {
}
