package ai.fassto.connectivity.dataaccess.salesorder.dto;

import lombok.Builder;

@Builder
public class OutInventoryAssignDeleteDTO {
    private String outOrdSlipNo;
    private String whCd;
}
