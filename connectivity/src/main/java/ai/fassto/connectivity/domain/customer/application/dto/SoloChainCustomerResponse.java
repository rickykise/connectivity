package ai.fassto.connectivity.domain.customer.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SoloChainCustomerResponse(
        String cstCd,
        String cstNm
) {
    private static final String CREATE_MESSAGE = "Customer is created";
    private static final String CREATE_MESSAGE_KO = "고객사 생성이 완료되었습니다.";

    private static final String UPDATE_MESSAGE = "Customer is updated.";
    private static final String UPDATE_MESSAGE_KO = "고객사 수정이 완료되었습니다.";

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
}
