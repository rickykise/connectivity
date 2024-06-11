package ai.fassto.connectivity.externalservice.wms.solochain.item.dto;

import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.MMCustomerRelAttributes;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.MMSiteAttributes;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.MMVendorRelAttributes;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.MaterialMaster;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class MaterialMasterB implements MaterialMaster {
    @JsonProperty("PartNo")
    private String partNo_;

    @JsonProperty("MMCustomerRelAttributes")
    private MMCustomerRelAttributes mmCustomerRelAttributes_;

    @JsonProperty("MMVendorRelAttributes")
    private MMVendorRelAttributes mmVendorRelAttributes_;

    @JsonProperty("MMSiteAttributes")
    private MMSiteAttributes mmSiteAttributes_;
}
