package ai.fassto.connectivity.dataaccess.common.dto;

import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DelayArrivalTalkDTO {
    private String ordDt;
    private String slipNo;
    private String warehouseCode;
    private String cstCd;
    private String cenArvTime;
    private ReleaseType releaseType;
}
