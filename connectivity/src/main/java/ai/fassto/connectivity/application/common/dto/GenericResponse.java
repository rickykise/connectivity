package ai.fassto.connectivity.application.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    private String message;
    private String result = "success";
    private Integer status;
    private String statusReason;
    private T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime responseAt = LocalDateTime.now();

    public GenericResponse(String message) {
        this.message = message;
    }

    public GenericResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public GenericResponse(String message, T data, HttpStatus httpStatus) {
        this.message = message;
        this.data = data;
        this.status = httpStatus.value();
        this.statusReason = httpStatus.getReasonPhrase();
    }
}
