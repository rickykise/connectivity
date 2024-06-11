package ai.fassto.connectivity.domain.salesorder.application.dto.erp.update;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record UpdateErpSalesOrderResponse(
    String salesOrderId,
    String orderNo,
    String customerCode
) {
    private static final String UPDATE_SALES_ORDER_MESSAGE = "Sales order status has been updated";
    private static final String UPDATE_SALES_ORDER_MESSAGE_KO = "출고 주문상태 변경이 완료되었습니다.";

    public static String getUpdateSalesOrderResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return UPDATE_SALES_ORDER_MESSAGE_KO;
        }
        return UPDATE_SALES_ORDER_MESSAGE;
    }
}
