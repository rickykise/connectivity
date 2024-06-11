package ai.fassto.connectivity.domain.parcel.application.dto.carrier;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record CreateCarrierParcelReturnInvoiceResponse(

) {
    private static final String CREATE_RETURN_INVOICE_MESSAGE = "Return invoice registration completed";
    private static final String CREATE_RETURN_INVOICE_MESSAGE_KO = "반품접수가 완료되었습니다.";

    public static String getCreateReturnInvoiceResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return CREATE_RETURN_INVOICE_MESSAGE_KO;
        }
        return CREATE_RETURN_INVOICE_MESSAGE;
    }
}
