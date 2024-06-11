package ai.fassto.connectivity.domain.item.application.dto.solochain;

import ai.fassto.connectivity.domain.item.application.valueobject.ResponseItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record SoloChainItemResponse(
        List<ResponseItem> items
) {
    private static final String CREATE_MESSAGE = "Item is created";
    private static final String CREATE_MESSAGE_KO = "상품 생성이 완료되었습니다.";

    private static final String UPDATE_MESSAGE = "Item is updated.";
    private static final String UPDATE_MESSAGE_KO = "상품 수정이 완료되었습니다.";

    private static final String DELETE_MESSAGE = "Item is deleted.";
    private static final String DELETE_MESSAGE_KO = "상품 삭제가 완료되었습니다.";

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
