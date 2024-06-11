package ai.fassto.connectivity.domain.parcel.application;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.YlpParcelApplicationService;
import ai.fassto.connectivity.domain.parcel.application.port.output.external.api.carrier.VehicleCarrier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YlpParcelApplicationServiceImpl implements YlpParcelApplicationService {

    private final VehicleCarrier vehicleCarrier;

    @Override
    public VehicleAllocationYlpResponse createVehicleAllocate(VehicleAllocationYlpRequest request) {
        return vehicleCarrier.sendVehicleAllocate(request);
    }

}
