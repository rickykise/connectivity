package ai.fassto.connectivity.domain.status.application.dto;

import ai.fassto.connectivity.common.configuration.EnvironmentComponent;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HealthResponse {
    private String name;
    private String version;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;

    public HealthResponse() {
        this.name = EnvironmentComponent.PROJECT_NAME;
        this.version = EnvironmentComponent.VERSION;
        this.description = EnvironmentComponent.DESCRIPTION;
        this.timestamp = LocalDateTime.now();
    }
}
