package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.OrderType;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Requester;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Supplier;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLH(
        @JsonProperty("Lifecycle")
        String lifecycle_, //  "Internal",

        @JsonProperty("OrderClass")
        String orderClass_, //  "Transfer",

        @JsonProperty("OrderNo")
        String orderNo_, // "DG-WO-20221216-13",

        @JsonProperty("OrderType")
        OrderType orderType_,

        @JsonProperty("Supplier")
        Supplier supplier_,

        @JsonProperty("Requester")
        Requester requester_,

        @JsonProperty("RequiredDate")
        String requiredDate_,

        @JsonProperty("InternalRequest")
        InternalRequest internalRequest_,

        @JsonProperty("GetSOLIByParent")
        GetSOLIByParent getSOLIByParent_
) {

}
