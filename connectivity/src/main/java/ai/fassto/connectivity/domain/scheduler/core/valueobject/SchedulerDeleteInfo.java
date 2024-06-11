package ai.fassto.connectivity.domain.scheduler.core.valueobject;

import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SchedulerDeleteInfo {
    private String type;
    private String taskId;
    private LocalDateTime createdAt;
}
