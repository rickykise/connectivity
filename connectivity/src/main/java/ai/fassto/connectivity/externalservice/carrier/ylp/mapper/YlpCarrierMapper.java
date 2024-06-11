package ai.fassto.connectivity.externalservice.carrier.ylp.mapper;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse;
import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationRequest;
import ai.fassto.connectivity.externalservice.carrier.ylp.dto.VehicleAllocationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class YlpCarrierMapper {
    private final ModelMapper mapper;

    public VehicleAllocationRequest vehicleAllocationYlpRequestToVehicleAllocationRequest(VehicleAllocationYlpRequest request) {
        return mapper.map(request, VehicleAllocationRequest.class);
    }

    public VehicleAllocationYlpResponse vehicleAllocationResponseToVehicleAllocationYlpResponse(VehicleAllocationResponse response) {
        return VehicleAllocationYlpResponse.builder().code(response.getCode()).build();
    }

}
