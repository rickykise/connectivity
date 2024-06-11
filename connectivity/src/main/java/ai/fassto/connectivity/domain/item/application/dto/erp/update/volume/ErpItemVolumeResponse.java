package ai.fassto.connectivity.domain.item.application.dto.erp.update.volume;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record ErpItemVolumeResponse(
        String itemCode
) {
    private static final String UPDATE_VOLUME_MESSAGE = "Item volume is updated.";
    private static final String UPDATE_VOLUME_MESSAGE_KO = "상품 체적정보 수정이 완료되었습니다.";

    public static String getUpdateVolumeResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return UPDATE_VOLUME_MESSAGE_KO;
        }
        return UPDATE_VOLUME_MESSAGE;
    }
}
