package ai.fassto.connectivity.externalservice.wms.solochain.vehicle.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record TransportEquipment(
        @JsonProperty("Name")
        String name_
) {
}
