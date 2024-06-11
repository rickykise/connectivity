package ai.fassto.connectivity.domain.stock.application.dto.erp.update;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record UpdateErpStockResponse() {
    private static final String UPDATE_STOCK_MESSAGE = "Stock has been updated";
    private static final String UPDATE_STOCK_MESSAGE_KO = "재고 변경이 완료되었습니다.";

    public static String getUpdateStockResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return UPDATE_STOCK_MESSAGE_KO;
        }
        return UPDATE_STOCK_MESSAGE;
    }
}
