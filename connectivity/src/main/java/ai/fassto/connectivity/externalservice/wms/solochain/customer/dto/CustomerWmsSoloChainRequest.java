package ai.fassto.connectivity.externalservice.wms.solochain.customer.dto;

import ai.fassto.connectivity.externalservice.wms.solochain.customer.valueobject.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWmsSoloChainRequest {
        @JsonProperty("transactions")
        List<Transaction> transactions_;


}
