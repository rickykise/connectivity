package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Category (
        @JsonProperty("Code")
        String code_
){
}
