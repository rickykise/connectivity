package ai.fassto.connectivity.externalservice.carrier.tms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TmsErrorResponse {
    private String timeStamp;
    private String traceId;
    private String errorCode;
    private String errorMessage;
}
