package ai.fassto.connectivity.domain.vendor.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SoloChainVendorResponse(
        String supCd,
        String supNm
) {
    private static final String CREATE_MESSAGE = "Vendor is created";
    private static final String CREATE_MESSAGE_KO = "공급사 생성이 완료되었습니다.";

    private static final String UPDATE_MESSAGE = "Vendor is updated.";
    private static final String UPDATE_MESSAGE_KO = "공급사 수정이 완료되었습니다.";

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
