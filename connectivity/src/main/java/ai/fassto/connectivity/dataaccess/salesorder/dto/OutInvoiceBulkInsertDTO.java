package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutInvoiceEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OutInvoiceBulkInsertDTO {
    private String outDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String shopCd;
    private String outOrdSlipNo;
    private List<OutInvoiceEntity> outInvoiceEntityList;

    @Builder
    public OutInvoiceBulkInsertDTO(String outDt, String whCd, String slipNo, String cstCd, String shopCd, String outOrdSlipNo, List<OutInvoiceEntity> outInvoiceEntityList) {
        this.outDt = outDt;
        this.whCd = whCd;
        this.slipNo = slipNo;
        this.cstCd = cstCd;
        this.shopCd = shopCd;
        this.outOrdSlipNo = outOrdSlipNo;
        this.outInvoiceEntityList = outInvoiceEntityList;
    }
}
