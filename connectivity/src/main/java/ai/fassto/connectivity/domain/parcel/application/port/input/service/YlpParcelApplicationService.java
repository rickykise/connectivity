package ai.fassto.connectivity.domain.parcel.application.port.input.service;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse;

public interface YlpParcelApplicationService {
    VehicleAllocationYlpResponse createVehicleAllocate(VehicleAllocationYlpRequest request);

}
