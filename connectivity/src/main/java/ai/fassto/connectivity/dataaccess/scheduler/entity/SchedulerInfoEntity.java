package ai.fassto.connectivity.dataaccess.scheduler.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerInfoEntity {

    private String type;
    private String taskId;
    private LocalDateTime createdAt;
    private Integer totalCount;
    private Integer registeredCount;
    private Integer inProgressCount;

}
