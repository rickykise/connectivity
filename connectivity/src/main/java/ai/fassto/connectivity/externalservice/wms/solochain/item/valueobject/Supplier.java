package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Supplier(
        @JsonProperty("Class")
        String className_,

        @JsonProperty("AccountNo")
        String accountNo_
) {
}
