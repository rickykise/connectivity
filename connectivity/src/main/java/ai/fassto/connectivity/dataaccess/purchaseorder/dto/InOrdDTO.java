package ai.fassto.connectivity.dataaccess.purchaseorder.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InOrdDTO {
    private String whCd;
    private String slipNo;
}
