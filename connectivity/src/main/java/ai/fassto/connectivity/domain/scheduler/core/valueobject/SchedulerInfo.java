package ai.fassto.connectivity.domain.scheduler.core.valueobject;

import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SchedulerInfo {

    private SchedulerType type;
    private SchedulerId taskId;
    private LocalDateTime createdAt;
    private Integer totalCount;
    private Integer registeredCount;
    private Integer inProgressCount;

}
