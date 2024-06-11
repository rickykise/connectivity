package ai.fassto.connectivity.domain.parcel.application.port.input.service;

import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoResponse;

public interface VehicleAllocationService {
    SoloChainVehicleAllocationInfoResponse updateVehicleAllocationInfo(SoloChainVehicleAllocationInfoRequest request);
}
