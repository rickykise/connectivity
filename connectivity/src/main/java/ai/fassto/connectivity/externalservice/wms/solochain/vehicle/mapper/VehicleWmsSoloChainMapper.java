package ai.fassto.connectivity.externalservice.wms.solochain.vehicle.mapper;

import ai.fassto.connectivity.domain.parcel.application.dto.solochain.VehicleAllocationInfo;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.dto.VehicleAllocationInfoWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleWmsSoloChainMapper {

    public VehicleAllocationInfoWmsSoloChainRequest vehicleAllocationInfoUpdateRequestToVehicleAllocationInfoWmsSoloChainRequest(SoloChainVehicleAllocationInfoRequest request) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.builder()
                .loads_(toLoads(request))
                .build()
        );
        return new VehicleAllocationInfoWmsSoloChainRequest(transactions);
    }

    private List<LOAD> toLoads(SoloChainVehicleAllocationInfoRequest request) {
        List<LOAD> loads = request.getVehicleAllocationInfoList().stream().map(this::toLoad).toList();
        return loads;
    }

    private LOAD toLoad(VehicleAllocationInfo vehicleAllocationInfo) {
        return LOAD.builder()
                .reservationNumber_(vehicleAllocationInfo.getArrangeNo())
                .referenceNo_(vehicleAllocationInfo.getCode())
                .site_(Site.builder().accountNo_(vehicleAllocationInfo.getWareHouseCode()).build())
                .transportEquipment_(TransportEquipment.builder()
                        .name_(vehicleAllocationInfo.getCarType()+"-"+vehicleAllocationInfo.getCarWeight())
                        .build())
                .reservedVehicleLP_(vehicleAllocationInfo.getCarNumber())
                .reservedVehicleDriver_(vehicleAllocationInfo.getDriverName())
                .reservedVehiclePhone_(vehicleAllocationInfo.getDriverPhoneNum())
                .type_(Type.builder().name_("LOAD").build())
                .build();
    }

}
