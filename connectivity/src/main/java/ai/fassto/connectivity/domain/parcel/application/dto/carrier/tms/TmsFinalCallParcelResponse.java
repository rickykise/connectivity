package ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms;

import ai.fassto.connectivity.domain.parcel.application.valueobject.ShipResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record TmsFinalCallParcelResponse(
        @JsonProperty("ShipResponse")
        ShipResponse shipResponse_
) {
    private static final String TMS_FINAL_CALL_MESSAGE = "TMS-Final-Call completed";
    private static final String TMS_FINAL_CALL_MESSAGE_KO = "택배접수 및 송장등록이 완료되었습니다.";

    public static String getResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return TMS_FINAL_CALL_MESSAGE_KO;
        }
        return TMS_FINAL_CALL_MESSAGE;
    }
}
