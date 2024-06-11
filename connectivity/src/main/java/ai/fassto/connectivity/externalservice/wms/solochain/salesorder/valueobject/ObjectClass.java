package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Attr;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.OrderType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ObjectClass(
        @JsonProperty("@attr")
        Attr attr_,
        @JsonProperty("Lifecycle")
        String lifecycle_, // Outbound",
        @JsonProperty("OrderClass")
        String orderClass_, // "Sales",
        @JsonProperty("OrderNo")
        String orderNo_, // : "SO-DG-JSON1",
        @JsonProperty("OrderType")
        OrderType orderType_
) {
}
