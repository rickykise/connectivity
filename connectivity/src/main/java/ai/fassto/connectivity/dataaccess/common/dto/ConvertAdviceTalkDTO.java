package ai.fassto.connectivity.dataaccess.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConvertAdviceTalkDTO {
    private String ordDt;
    private String slipNo;
    private String cstCd;
    private String cenArvTime;
    private String releaseDt;
}
