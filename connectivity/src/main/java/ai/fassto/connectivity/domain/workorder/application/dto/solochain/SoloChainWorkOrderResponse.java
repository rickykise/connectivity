package ai.fassto.connectivity.domain.workorder.application.dto.solochain;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record SoloChainWorkOrderResponse(
        String slipNo
) {
    private static final String CREATE_MESSAGE = "Work Order is created";
    private static final String CREATE_MESSAGE_KO = "유통가공작업 생성이 완료되었습니다.";

    private static final String UPDATE_MESSAGE = "Work Order is updated.";
    private static final String UPDATE_MESSAGE_KO = "유통가공작업 수정이 완료되었습니다.";

    private static final String DELETE_MESSAGE = "Work Order is deleted.";
    private static final String DELETE_MESSAGE_KO = "유통가공작업 삭제가 완료되었습니다.";


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
