package ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@JsonInclude(NON_NULL)
public record PatchSoloChainPurchaseOrderQaOnHoldItemsResponse(
        String slipNo,
        String receiptNo,
        String status
) {
    private static final String PATCH_MESSAGE = "QA on hold items confirmation has been changed";
    private static final String PATCH_MESSAGE_KO = "입고보류상태가 변경되었습니다.";


    public static String getPatchResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return PATCH_MESSAGE_KO;
        }
        return PATCH_MESSAGE;
    }
}
