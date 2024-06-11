package ai.fassto.connectivity.externalservice.carrier.ylp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class VehicleAllocationRequest {
    private String warehouseCode;
    private String code;
    private List<Order> orders;
    private List<CarData> carData;
    private List<OutData> outData;
}
