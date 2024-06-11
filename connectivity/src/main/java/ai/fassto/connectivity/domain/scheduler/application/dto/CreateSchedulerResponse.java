package ai.fassto.connectivity.domain.scheduler.application.dto;

import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerId;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;

public record CreateSchedulerResponse(
        SchedulerType type,
        SchedulerId taskId
) {

    private static final String CREATED_MESSAGE = "스케줄 등록이 완료 되었습니다.";

    public static String getCreatedMessage() {
        return CREATED_MESSAGE;
    }

}
