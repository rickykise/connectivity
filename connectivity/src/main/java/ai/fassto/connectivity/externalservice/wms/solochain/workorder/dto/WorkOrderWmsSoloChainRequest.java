package ai.fassto.connectivity.externalservice.wms.solochain.workorder.dto;


import ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderWmsSoloChainRequest {
    @JsonProperty("transactions")
    private List<Transaction> transactions_;
}
