package ai.fassto.connectivity.domain.parcel.application.port.output.external.api.carrier;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse;

public interface VehicleCarrier {
    VehicleAllocationYlpResponse sendVehicleAllocate(VehicleAllocationYlpRequest request);
}
