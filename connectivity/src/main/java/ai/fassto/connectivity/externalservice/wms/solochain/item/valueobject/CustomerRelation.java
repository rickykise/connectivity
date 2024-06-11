package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Customer;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.MaterialMaster;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record CustomerRelation(

        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,

        @JsonProperty("Supplier")
        Supplier supplier_,

        @JsonProperty("Customer")
        Customer customer_
) {

}
