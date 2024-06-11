package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class WOHA implements WOH {
    @JsonProperty("OrderClass")
    String orderClass_;

    @JsonProperty("ReferenceNo")
    String referenceNo_;

    @JsonProperty("Type")
    Type type_;

    @JsonProperty("Supplier")
    Supplier supplier_;

    @JsonProperty("Requester")
    Requester requester_;

    @JsonProperty("ShipTo")
    ShipTo shipTo_;

    @JsonProperty("RequiredDate")
    String requiredDate_;

    @JsonProperty("MaterialMaster")
    MaterialMaster materialMaster_;

    @JsonProperty("Quantity")
    Integer quantity_;

    @JsonProperty("Status")
    String status_;

    @JsonProperty("UOI")
    UOI uoi_;

    @JsonProperty("WorkCenter")
    WorkCenter workCenter_;

    @JsonProperty("WOS")
    WOS wos_;
}
