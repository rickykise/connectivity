package ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Item {
    @ApiModelProperty(required = true, notes = "Item ID (상품 코드)", example = "07970ELS00002")
    private String code; // "01232AAAA01",

    @ApiModelProperty(required = true, notes = "Item Quantity (물품 수량)", example = "2")
    private Integer qty; // 1
}
