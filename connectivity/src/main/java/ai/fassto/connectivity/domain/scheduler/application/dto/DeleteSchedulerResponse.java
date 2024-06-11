package ai.fassto.connectivity.domain.scheduler.application.dto;

import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerId;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record DeleteSchedulerResponse(
        SchedulerType type,
        SchedulerId taskId
) {
    private static final String DELETE_MESSAGE = "스케줄 삭제가 완료되었습니다. 진행중인 작업은 취소되지 않습니다.";

    public static String getDeleteResponseMessage() {
        return DELETE_MESSAGE;
    }

}
