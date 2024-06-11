package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.dto;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class PurchaseOrderWmsSoloChainResponse {
    @JsonProperty("Result")
    private String result;

    @JsonProperty("Messages")
    private List<Message> messages;

    @JsonProperty("ID")
    private String id;

    @JsonProperty("ExternalID")
    private String externalId;
}
