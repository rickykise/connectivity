package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record Transaction(
        @JsonProperty("SOLH")
        List<SOLH> solhs_,
        @JsonProperty("FileDocument")
        List<FileDocument> fileDocument_
) {
}
