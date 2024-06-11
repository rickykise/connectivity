package ai.fassto.connectivity.dataaccess.salesorder.dto;

import lombok.Builder;

@Builder
public class OutOrdParcelCdInvoiceNoDTO {
    private String outOrdSlipNo;
    private String whCd;
    private String parcelCd;
    private String invoiceNo;
    private String ordDt;
}
