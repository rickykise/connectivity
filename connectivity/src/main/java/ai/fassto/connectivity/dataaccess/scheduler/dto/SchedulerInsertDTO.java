package ai.fassto.connectivity.dataaccess.scheduler.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerInsertDTO {

    private String type;
    private String status;
    private String taskId;
    private String meta;
    private String email;
    private LocalDateTime reservedAt;

}
