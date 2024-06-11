package ai.fassto.connectivity.domain.salesorder.application.dto.erp.update;


import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.domain.common.exception.NotBlankException;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus.*;

@Getter
@Setter
@NoArgsConstructor
public class Item {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Item code (상품 코드)", example = "01076HAAB00026")
    private String code;

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(notes = "Sequence (시퀀스)", example = "1")
    private Integer sequence;

    @ApiModelProperty(notes = "order quantity (Required at Picked/Packed) (주문 수량 - Picked/Packed 인경우 필수)", example = "30")
    private String qty;

    @ApiModelProperty(notes = "Item lot (상품 lot)", example = "20221222")
    private String lot;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(notes = "Expiry Date (유통기한)", example = "2024-01-10 00:00:00.000")
    private LocalDateTime expiryDate;

    @ApiModelProperty(notes = "Box Sequence (박스 시퀀스)", example = "1")
    private Integer boxSeq;

    public void validate(String orderStatus) {
        code = StringUtils.trim(code);
        qty = StringUtils.trim(qty);

        if (OUTSTANDING.isNot(orderStatus)) {
            if (StringUtils.isBlank(lot)) {
                throw new NotBlankException("lot", String.format("When OrderStatus is \"%s\", lot cannot be null", orderStatus));
            }
            if (StringUtils.isBlank(qty)) {
                throw new NotBlankException("qty", String.format("When OrderStatus is \"%s\", qty cannot be null", orderStatus));
            }
        }

    }

}