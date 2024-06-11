package ai.fassto.connectivity.domain.parcel.application.dto.solochain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Setter
@Getter
public class SoloChainVehicleAllocationInfoRequest {

    @Valid
    private List<VehicleAllocationInfo> vehicleAllocationInfoList;
}
