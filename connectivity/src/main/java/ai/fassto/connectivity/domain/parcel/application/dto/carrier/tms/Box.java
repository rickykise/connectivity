package ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Box {
    @ApiModelProperty(required = true, notes = "BOX ID (패킹 박스 ID)", example = "id1284179234712")
    private String boxID; //  id1284179234712
    @ApiModelProperty(notes = "BOX Type (박스 사이즈 - SS, M, L, S, MS, 2XL, LL, XL, A)", example = "L")
    private String type; //
    @ApiModelProperty(notes = "BOX Category (박스 카테고리 )", example = "Carton")
    private String category; //
    @ApiModelProperty(notes = "Invoice Number (송장번호)", example = "1314151617")
    private String invoiceNo; // "invoice1",
    @ApiModelProperty(notes = "Product Quantity (상품수량 EA)", example = "4")
    private Integer productQty; // 4,
    @ApiModelProperty(notes = "Box Width (박스 가로길이 cm)", example = "100.0")
    private Float boxWidth; //  100,
    @ApiModelProperty(notes = "Box Height (박스 높이 cm)", example = "20.0")
    private Float boxHeight; //  20,
    @ApiModelProperty(notes = "Box Depth (박스 깊이 cm)", example = "10.0")
    private Float boxDepth; //  10,
    @ApiModelProperty(notes = "Box weight (박스 무게 g)", example = "100")
    private Integer boxWeight; //  100
    @ApiModelProperty(required = true, notes = "Packed Item List (패킹한 상품 목록)")
    private List<Item> items;

    public Box(String invoiceNo, Integer productQty) {
        this.invoiceNo = invoiceNo;
        this.productQty = productQty;
        this.items = new ArrayList<>();
    }
}
