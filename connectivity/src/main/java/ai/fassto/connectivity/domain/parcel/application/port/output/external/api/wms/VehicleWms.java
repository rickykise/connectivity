package ai.fassto.connectivity.domain.parcel.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoResponse;

public interface VehicleWms {
    SoloChainVehicleAllocationInfoResponse updateVehicleAllocationInfo(SoloChainVehicleAllocationInfoRequest request);
}
