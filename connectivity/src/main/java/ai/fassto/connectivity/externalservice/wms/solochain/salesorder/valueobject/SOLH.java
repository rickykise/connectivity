package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLH(
        @JsonProperty("Lifecycle")
        String lifecycle_, // "Outbound",
        @JsonProperty("OrderClass")
        String orderClass_, // "Sales",
        @JsonProperty("OrderNo")
        String orderNo_, // "SO-DG-JSON1",
        @JsonProperty("Priority")
        Integer priority_, // 2,
        @JsonProperty("OrderType")
        OrderType orderType_,
        @JsonProperty("RequiredDate")
        String requiredDate_, // : "2022-11-09",
        @JsonProperty("Status")
        String status_, // : "ACTIVE",
        @JsonProperty("Supplier")
        Supplier supplier_,
        @JsonProperty("Requester")
        Requester requester_,
        @JsonProperty("Owner")
        Owner owner_,
        @JsonProperty("ReferenceNo")
        String referenceNo_, // "SO-DG-JSON1",
        @JsonProperty("CustomerBrandedBox")
        String customerBrandedBox_, // "SO-DG-JSON1",
        @JsonProperty("BoxCategory")
        String boxCategory_, // "SO-DG-JSON1",
        @JsonProperty("MaxShipmentQuantity")
        Integer maxShipmentQuantity_, // 1 고정
        @JsonProperty("TrackingNumber")
        String trackingNumber_, // "SO-DG-JSON1",
        @JsonProperty("ShipmentRequest")
        ShipmentRequest shipmentRequest_,
        @JsonProperty("OrderInstruction")
        List<OrderInstruction> orderInstruction_,
        @JsonProperty("GetSOLIByParent")
        GetSOLIByParent getSOLIByParent_
) {
}
