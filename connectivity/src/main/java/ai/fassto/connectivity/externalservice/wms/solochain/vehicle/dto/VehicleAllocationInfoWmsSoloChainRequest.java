package ai.fassto.connectivity.externalservice.wms.solochain.vehicle.dto;

import ai.fassto.connectivity.externalservice.wms.solochain.vehicle.valueobject.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class VehicleAllocationInfoWmsSoloChainRequest {
    @JsonProperty("transactions")
    private List<Transaction> transactions_;
}
