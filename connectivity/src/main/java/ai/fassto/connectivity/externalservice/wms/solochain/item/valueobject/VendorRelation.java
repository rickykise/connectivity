package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.MaterialMaster;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Receiver;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Vendor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record VendorRelation(

        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,

        @JsonProperty("Receiver")
        Receiver receiver_,

        @JsonProperty("Vendor")
        Vendor vendor_
) {

}
