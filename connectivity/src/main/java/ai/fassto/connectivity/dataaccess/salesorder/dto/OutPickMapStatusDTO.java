package ai.fassto.connectivity.dataaccess.salesorder.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OutPickMapStatusDTO {
    private String whCd;
    private String outOrdSlipNo;
    private String allocateYn;
}
