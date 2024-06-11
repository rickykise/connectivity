package ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record VehicleAllocationYlpResponse(
    String code
) {
    private static final String VEHICLE_ALLOCATION_YLP_MESSAGE = "Vehicle Allocation YLP registered";
    private static final String VEHICLE_ALLOCATION_YLP_MESSAGE_KO = "YLP 차량 배차 신청이 등록되었습니다.";

    public static String getVehicleAllocationYlpResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return VEHICLE_ALLOCATION_YLP_MESSAGE_KO;
        }
        return VEHICLE_ALLOCATION_YLP_MESSAGE;
    }
}
