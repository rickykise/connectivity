package ai.fassto.connectivity.domain.item.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class SoloChainItemRequest {
    @Valid
    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @Size(min = 1, message = "{ai.fassto.connectivity.constraints.Size.list.min.message}", groups = {MinMaxGroup.class})
    @ApiModelProperty(required = true, notes = "Goods list (상품 리스트)")
    private List<RequestItem> items;

    public SoloChainItemRequest validate() {
        for (RequestItem item : items) {
            item.valid();
        }

        return this;
    }
}
