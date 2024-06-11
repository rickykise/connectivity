package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ProductCategory(
        @JsonProperty("Category")
        Category category_,
        @JsonProperty("Code")
        String code_,
        @JsonProperty("Name")
        String name_
) {
}
