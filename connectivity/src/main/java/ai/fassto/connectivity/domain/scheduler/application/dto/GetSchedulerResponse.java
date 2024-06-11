package ai.fassto.connectivity.domain.scheduler.application.dto;

import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerId;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record GetSchedulerResponse(
        SchedulerType type,
        SchedulerId taskId,
        LocalDateTime createdAt,
        Integer totalCount,
        Integer registeredCount,
        Integer inProgressCount
) {
    private static final String GET_MESSAGE = "조회가 완료되었습니다.";

    public static String getGetResponseMessage() {
        return GET_MESSAGE;
    }

}
