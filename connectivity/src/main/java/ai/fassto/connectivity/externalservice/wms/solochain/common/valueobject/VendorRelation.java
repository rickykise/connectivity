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
public class VendorRelation{

    @JsonProperty("MaterialMaster")
    private MaterialMasterPartNo materialMaster;

    @JsonProperty("Receiver")
    private Receiver receiver;

    @JsonProperty("Vendor")
    private Vendor vendor;

    public VendorRelation(Receiver receiver, Vendor vendor) {
        this.receiver = receiver;
        this.vendor = vendor;
    }
}