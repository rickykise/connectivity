package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLI(
        @JsonProperty("Sequence")
        Integer sequence_, //  1\

        @JsonProperty("Status")
        String status_, // "ACTIVE"

        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,


        @JsonProperty("Quantity")
        Integer quantity_, // 10

        @JsonProperty("ReleasedQty")
        Integer releasedQty_, // : 10,

        @JsonProperty("UOI")
        UOI uoi_,

        @JsonProperty("Requester")
        Requester requester_,

        @JsonProperty("Supplier")
        Supplier supplier_,

        @JsonProperty("Owner")
        Owner owner_,

        @JsonProperty("ShipTo")
        ShipTo shipTo_,

        @JsonProperty("SOLIDimension")
        SOLIDimension soliDimension_,

        @JsonProperty("ShipmentRequest")
        ShipmentRequest shipmentRequest_,

        @JsonProperty("WOS")
        WOSSimple wos_

) {
}
