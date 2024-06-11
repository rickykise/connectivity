package ai.fassto.connectivity.externalservice.wms.solochain.item.dto;

import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record ItemWmsSoloChainRequest(

        @JsonProperty("transactions")
        List<Transaction> transactions_
) {
}
