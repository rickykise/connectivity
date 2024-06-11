package ai.fassto.connectivity.domain.scheduler.application.dto;

import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerInfo;

import java.util.List;

public record SearchSchedulerResponse(
        List<SchedulerInfo> schedulerInfoList
) {
    private static final String GET_MESSAGE = "조회가 완료되었습니다.";

    public static String getGetResponseMessage() {
        return GET_MESSAGE;
    }
}
