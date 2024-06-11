package ai.fassto.connectivity.dataaccess.scheduler.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerDeleteDTO {
    private String status;
    private String taskId;
    private String description;
}
