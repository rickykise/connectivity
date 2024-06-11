package ai.fassto.connectivity.dataaccess.common.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CenArrivalTalkDTO {
    private String ordDt;
    private String slipNo;
    private String cstCd;
    private String releaseDt;
}
