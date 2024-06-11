package ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms;

import ai.fassto.connectivity.domain.parcel.application.valueobject.SOLH;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record TmsPreCallParcelResponse(
        @JsonProperty("SOLH")
        SOLH solh_,
        @JsonIgnore
        String deliveryCompanyCode
) {
    private static final String TMS_PRE_CALL_MESSAGE = "TMS-Pre-Call completed";
    private static final String TMS_PRE_CALL_MESSAGE_KO = "송장번호 생성이 완료되었습니다.";

    public static String getResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return TMS_PRE_CALL_MESSAGE_KO;
        }
        return TMS_PRE_CALL_MESSAGE;
    }
}
