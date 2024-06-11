package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLI(
        @JsonProperty("Sequence")
        String sequence_,
        @JsonProperty("ShipmentRequest")
        ShipmentRequestSimple shipmentRequest_,
        @JsonProperty("Status")
        String status_,
        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,
        @JsonProperty("UOI")
        UOI uoi_,
        @JsonProperty("Quantity")
        Integer quantity_,
        @JsonProperty("Owner")
        Owner owner_,
        @JsonProperty("Condition")
        Condition condition_,
        @JsonProperty("ShipTo")
        ShipTo shipTo_,
        @JsonProperty("SOLIDimension")
        SOLIDimension soliDimension_
) {
}
