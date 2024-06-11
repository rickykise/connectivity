package ai.fassto.connectivity.externalservice.wms.solochain.vehicle.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record LOAD(
        @JsonProperty("ReservationNumber")
        String reservationNumber_,

        @JsonProperty("ReferenceNo")
        String referenceNo_,

        @JsonProperty("Site")
        Site site_,

        @JsonProperty("TransportEquipment")
        TransportEquipment transportEquipment_,

        @JsonProperty("ReservedVehicleLP")
        String reservedVehicleLP_,

        @JsonProperty("ReservedVehicleDriver")
        String reservedVehicleDriver_,

        @JsonProperty("ReservedVehiclePhone")
        String reservedVehiclePhone_,

        @JsonProperty("Type")
        Type type_
) {
}
