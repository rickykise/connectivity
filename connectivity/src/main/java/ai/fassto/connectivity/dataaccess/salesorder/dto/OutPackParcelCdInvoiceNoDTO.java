package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPackEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class OutPackParcelCdInvoiceNoDTO {
    private String whCd;
    private String cstCd;
    private String outOrdSlipNo;
    private List<OutPackEntity> outPackEntityList;
}
