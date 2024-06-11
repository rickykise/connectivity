package ai.fassto.connectivity.domain.parcel.application;

import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.VehicleAllocationService;
import ai.fassto.connectivity.domain.parcel.application.port.output.external.api.wms.VehicleWms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleAllocationServiceImpl implements VehicleAllocationService {
    private final VehicleWms vehicleWms;

    @Override
    public SoloChainVehicleAllocationInfoResponse updateVehicleAllocationInfo(SoloChainVehicleAllocationInfoRequest request) {
        return vehicleWms.updateVehicleAllocationInfo(request);
    }
}
