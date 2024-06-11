package ai.fassto.connectivity.domain.parcel.application.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@Builder
public record SOLH(
        @JsonProperty("OrderNo")
        @ApiModelProperty(notes = "Slip Number for the request, sent by Solochain", example = "10519IO123")
        String orderNo_,
        @JsonProperty("TrackingNumber")
        @ApiModelProperty(notes = "The invoiceNumber is saved on SOLH.", example = "tn123")
        String trackingNumber_
){

}
