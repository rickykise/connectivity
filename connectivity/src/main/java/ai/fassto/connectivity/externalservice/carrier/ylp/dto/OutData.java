package ai.fassto.connectivity.externalservice.carrier.ylp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class OutData {
    private String description;
    private String loadUnit;
    private String loadCnt;
    private String loadWeight;
    private String loadName;
    private String loadPhone;
    private LocalDateTime loadDate;
    private String loadEmail;
}
