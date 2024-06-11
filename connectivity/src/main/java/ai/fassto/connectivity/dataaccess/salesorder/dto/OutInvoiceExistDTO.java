package ai.fassto.connectivity.dataaccess.salesorder.dto;

import lombok.Builder;

@Builder
public class OutInvoiceExistDTO {
    private String whCd;
    private String outOrdSlipNo;
    private String cstCd;
}
