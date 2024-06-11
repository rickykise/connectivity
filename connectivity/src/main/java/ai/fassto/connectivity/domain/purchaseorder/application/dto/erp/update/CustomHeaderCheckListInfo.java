package ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update;

import ai.fassto.connectivity.domain.common.exception.NotBlankException;
import ai.fassto.connectivity.domain.common.exception.NotNullException;
import ai.fassto.connectivity.domain.common.exception.PositiveValueException;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType.CENTER_RETURN_ORDER;
import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType.CONSUMER_RETURN_ORDER;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class CustomHeaderCheckListInfo {
    @ApiModelProperty(required = true, notes = "Inbound Method (01:Parcel. 02: Vehicle)", example = "01")
    private String deliveryType;

    private Integer subBoxCnt;

    private Integer pltCnt;

    private Integer mixInCnt;

    private Integer weight5;
    private Integer weight10;
    private Integer weight15;
    private Integer weight20;
    private Integer weight20Over;
    private String returnBoxSize;
    private String returnCollectWay;
    private Integer freightCost;

    public void validate(String orderType) {
        deliveryType = StringUtils.trim(deliveryType);
        returnBoxSize = StringUtils.trim(returnBoxSize);
        returnCollectWay = StringUtils.trim(returnCollectWay);

        if (StringUtils.isBlank(deliveryType)) {
            throw new NotBlankException("customHeaderCheckListInfo", "deliveryType is null");
        }

        if (subBoxCnt == null) {
            throw new NotNullException("customHeaderCheckListInfo", "subBoxCnt is null");
        }

        if (pltCnt == null) {
            throw new NotNullException("customHeaderCheckListInfo", "pltCnt is null");
        }

        if (mixInCnt == null) {
            throw new NotNullException("customHeaderCheckListInfo", "mixInCnt is null");
        }

        if (weight5 == null) {
            throw new NotNullException("customHeaderCheckListInfo", "weight5 is null");
        }

        if (PurchaseOrderType.findByType(orderType) == CONSUMER_RETURN_ORDER ||
            PurchaseOrderType.findByType(orderType) == CENTER_RETURN_ORDER
        ) {
            if (returnCollectWay == null) {
                throw new NotNullException("returnCollectWay", "returnCollectWay is null");
            }

            if (returnCollectWay.length() != 2) {
                throw new PositiveValueException("returnCollectWay", "When OrderType is \"CONSUMER_RETURN_ORDER\" or \"CENTER_RETURN_ORDER\", returnCollectWay length must be 2");
            }
        }
    }

}
