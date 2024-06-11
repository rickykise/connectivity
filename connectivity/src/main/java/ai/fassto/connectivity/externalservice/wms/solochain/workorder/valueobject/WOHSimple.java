package ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Supplier;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record WOHSimple(
        @JsonProperty("ReferenceNo")
        String referenceNo_,

        @JsonProperty("Supplier")
        Supplier supplier_
) {
}
