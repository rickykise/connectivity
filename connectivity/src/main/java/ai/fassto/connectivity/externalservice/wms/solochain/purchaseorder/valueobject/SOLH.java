package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Supplier;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.OrderInstruction;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.OrderType;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Requester;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SOLH(
        @JsonProperty("Lifecycle")
        String lifecycle_,

        @JsonProperty("OrderClass")
        String orderClass_,

        @JsonProperty("OrderNo")
        String orderNo_,

        @JsonProperty("Priority")
        String priority_,

        @JsonProperty("OrderType")
        OrderType orderType_,

        @JsonProperty("RequiredDate")
        String requiredDate_,

        @JsonProperty("Status")
        String status_,

        @JsonProperty("TrackingNumber")
        String trackingNumber_,

//        @JsonProperty("VehicleType") //  "courier",
        /*
        20223.03.06.JerryPark.  VehiculeType: "courier" in ICD Doc,
         */
        @JsonProperty("VehiculeType") //  "courier",
        String vehicleType_,

        @JsonProperty("OriginalCarrier") // : "FedEx",
        String originalCarrier_,

        @JsonProperty("OriginalTrackingNumber") // : "FEDEX1234567",
        String originalTrackingNumber_,

        @JsonProperty("ReturnReason") // : "exchange"
        String returnReason_,
        @JsonProperty("InboundTransportCompany")
        String inboundTransportCompany_,
        @JsonProperty("InboundLicensePlate")
        String inboundLicensePlate_,
        @JsonProperty("DriverContact")
        String driverContact_,
        @JsonProperty("Memo")
        String memo_,

        @JsonProperty("Supplier")
        Supplier supplier_,

        @JsonProperty("Requester")
        Requester requester_,

        @JsonProperty("OrderInstruction")
        List<OrderInstruction> orderInstruction_,

        @JsonProperty("GetSOLIByParent")
        GetSOLIByParent getSOLIByParent_
) {
}