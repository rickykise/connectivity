package ai.fassto.connectivity.domain.scheduler.application.dto;

import ai.fassto.connectivity.application.common.validation.group.MinMaxGroup;
import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class CreateSchedulerRequest {

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Scheduler type (스케줄러 유형: DAILY_STOCK ...)", example = "DAILY_STOCK")
    @Pattern(regexp = "^(DAILY_STOCK)$", message = "{ai.fassto.connectivity.constraints.PatternCheck.message}", groups = {PatternCheckGroup.class})
    private String schedulerType;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Email (등록자 이메일)", example = "your_fassto_email@fssuniverse.com")
    private String email;

    @Future(message = "{ai.fassto.connectivity.constraints.FutureOrPresent.message}", groups = {MinMaxGroup.class})
    @ApiModelProperty(notes = "Reserved at (예약시간: yyyy-MM-dd HH:mm:ss - 생략시: 바로실행)", example = "2023-03-18 00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime reservedAt;

    public CreateSchedulerRequest validate() {
        schedulerType = StringUtils.trim(schedulerType);
        email = StringUtils.trim(email);

        return this;
    }

}
