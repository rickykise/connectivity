package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record MMCustomerRelAttributes(
        @JsonProperty("CustomerExpiryDateTolerance")
        Integer customerExpiryDateTolerance_,

        @JsonProperty("CustomerExpiryDateToleranceMode")
        String customerExpiryDateToleranceMode_,
        @JsonProperty("CustomerRelation")
        CustomerRelation customerRelation_
) {
}
