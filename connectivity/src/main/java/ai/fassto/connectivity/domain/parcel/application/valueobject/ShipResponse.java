package ai.fassto.connectivity.domain.parcel.application.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record ShipResponse(
        @JsonProperty("ShipResult")
        ShipResult shipResult_
) {
}
