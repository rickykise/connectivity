package ai.fassto.connectivity.application.common.dto;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private String message;
    private String result = "failure";
    private int status;
    private String statusReason;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime errorAt;
    private List<ErrorDetail> errors;

    @Builder
    public ApiErrorResponse(String message, int status, String statusReason, String url, LocalDateTime errorAt, List<ErrorDetail> errors) {
        this.message = message;
        this.status = status;
        this.statusReason = statusReason;
        this.url = url;
        this.errorAt = errorAt;
        this.errors = errors;
    }
}
