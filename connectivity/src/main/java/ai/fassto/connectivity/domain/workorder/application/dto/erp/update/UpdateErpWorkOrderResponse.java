package ai.fassto.connectivity.domain.workorder.application.dto.erp.update;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record UpdateErpWorkOrderResponse(
        String workOrderId
) {
    private static final String UPDATE_WORK_ORDER_MESSAGE = "Work order status has been updated";
    private static final String UPDATE_WORK_ORDER_MESSAGE_KO = "유통가공작업 수정이 완료되었습니다.";

    public static String getUpdateSalesOrderResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return UPDATE_WORK_ORDER_MESSAGE_KO;
        }
        return UPDATE_WORK_ORDER_MESSAGE;
    }
}
