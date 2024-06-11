package ai.fassto.connectivity.domain.parcel.application.dto.solochain;

import ai.fassto.connectivity.domain.parcel.application.valueobject.AllocationInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.isAcceptLanguageKorean;

public record SoloChainVehicleAllocationInfoResponse(
        List<AllocationInfo> allocationInfos
) {

    private static final String VEHICLE_ALLOCATION_INFO_MESSAGE = "Vehicle Allocation Info completed";
    private static final String VEHICLE_ALLOCATION_INFO_MESSAGE_KO = "YLP 차량 배차가 완료되었습니다.";

    public static String getVehicleAllocationInfoResponseMessageBy(HttpServletRequest request) {
        if (isAcceptLanguageKorean(request)) {
            return VEHICLE_ALLOCATION_INFO_MESSAGE_KO;
        }
        return VEHICLE_ALLOCATION_INFO_MESSAGE;
    }
}
