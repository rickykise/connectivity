package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record SoloChainSalesOrdersResponse(
        List<SalesOrderResult> results
) {
    private static final String CREATE_MESSAGE = "Sales Order is created";
    private static final String CREATE_MESSAGE_KO = "출고 생성이 완료되었습니다.";

    private static final String UPDATE_MESSAGE = "Sales Order is updated.";
    private static final String UPDATE_MESSAGE_KO = "출고 수정이 완료되었습니다.";

    private static final String DELETE_MESSAGE = "Sales Order is deleted.";
    private static final String DELETE_MESSAGE_KO = "출고 삭제가 완료되었습니다.";

    public static String getCreateResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return CREATE_MESSAGE_KO;
        }
        return CREATE_MESSAGE;
    }

    public static String getUpdateResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return UPDATE_MESSAGE_KO;
        }
        return UPDATE_MESSAGE;
    }

    public static String getDeleteResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return DELETE_MESSAGE_KO;
        }
        return DELETE_MESSAGE;
    }
}
