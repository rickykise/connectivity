package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.PrimaryAddress;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Requester;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ShipFrom;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ShipTo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ShipmentRequest(
        @JsonProperty("ReferenceNo")
        String referenceNo_, // "SO-DG-JSON1",
        @JsonProperty("ShipByDate")
        String shipByDate_, // "2022-11-09",
        @JsonProperty("DeliverByDate")
        String deliverByDate_, // "2022-11-10",
        @JsonProperty("ChangeShippingOption")
        String changeShippingOption_, // Y/N
        @JsonProperty("Carrier")
        Carrier carrier_,
        @JsonProperty("ShippingServiceLevel")
        ShippingServiceLevel shippingServiceLevel_,
        @JsonProperty("ShipFrom")
        ShipFrom shipFrom_,
        @JsonProperty("Requester")
        Requester requester_,
        @JsonProperty("ShipTo")
        ShipTo shipTo_,
        @JsonProperty("ShipToName")
        String shipToName_, //  "M.testimport",
        @JsonProperty("ShipToPhoneNo")
        String shipToPhoneNo_, //  "82010123345",
        @JsonProperty("ShipToPhoneNo2")
        String shipToPhoneNo2_, //  "82010123345",
        @JsonProperty("ShipToAddress")
        PrimaryAddress shipToAddress_,
        @JsonProperty("ShipToInstruction")
        String shipToInstruction_,
        @JsonProperty("ShipToPassword")
        String shipToPassword_,
        @JsonProperty("SenderPhoneNumber")
        String senderPhoneNumber_
) {
}
