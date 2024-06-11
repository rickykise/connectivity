package ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateErpPurchaseOrderResponse(
        String purchaseOrderId
) {
    private static final String UPDATE_PURCHASE_ORDER_MESSAGE = "Purchase order status has been updated";
    private static final String UPDATE_PURCHASE_ORDER_MESSAGE_KO = "입고 주문상태 변경이 완료되었습니다.";

    public static String getUpdatePurchaseOrderResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return UPDATE_PURCHASE_ORDER_MESSAGE_KO;
        }
        return UPDATE_PURCHASE_ORDER_MESSAGE;
    }
}
