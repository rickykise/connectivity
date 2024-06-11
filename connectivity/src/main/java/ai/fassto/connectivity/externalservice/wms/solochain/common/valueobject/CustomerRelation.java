package ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRelation{
    @JsonProperty("MaterialMaster")
    private MaterialMasterPartNo materialMaster;

    @JsonProperty("Supplier")
    private Supplier supplier;

    @JsonProperty("Customer")
    private Customer customer;
}