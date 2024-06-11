package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record FileDocument(
        @JsonProperty("Object")
        ObjectClass objectClass_,
        @JsonProperty("Name")
        String name_, // REPORTNAME.pdf
        @JsonProperty("FileName")
        String fileName_, // REPORTNAME.pdf
        @JsonProperty("FileType")
        FileType fileType_,
        @JsonProperty("Repository")
        Repository repository_,
        @JsonProperty("DocumentType")
        DocumentType documentType_
) {
}
