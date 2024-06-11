package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ShipmentRequestSimple(
        @JsonProperty("@attr")
        Attr attr_,
        @JsonProperty("ReferenceNo")
        String referenceNo_,
        @JsonProperty("SOLH")
        SOLHSimple solh_,
        @JsonProperty("ShipFrom")
        ShipFrom shipFrom_,
        @JsonProperty("Status")
        String status_,
        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,
        @JsonProperty("UOI")
        UOI uoi_,
        @JsonProperty("Quantity")
        String quantity_,
        @JsonProperty("Owner")
        Owner owner_,
        @JsonProperty("ShipTo")
        ShipTo shipTo_
) {
}
