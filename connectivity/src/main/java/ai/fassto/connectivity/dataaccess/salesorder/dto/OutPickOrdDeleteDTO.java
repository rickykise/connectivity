package ai.fassto.connectivity.dataaccess.salesorder.dto;

import lombok.Builder;

@Builder
public class OutPickOrdDeleteDTO {
    private String whCd;
    private String outOrdSlipNo;
    private String cstCd;
}
