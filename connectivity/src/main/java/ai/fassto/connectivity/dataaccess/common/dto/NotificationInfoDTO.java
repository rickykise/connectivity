package ai.fassto.connectivity.dataaccess.common.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NotificationInfoDTO {
    private String ordDt;
    private String inDiv;
    private String slipNo;
    private String memo;
}
