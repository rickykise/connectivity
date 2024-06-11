package ai.fassto.connectivity.externalservice.wms.solochain.customer.valueobject;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record Supplier(
        @JsonProperty("@attr")
        Attr attr_,

        @JsonProperty("AccountNo")
        String accountNo_
) {
}
