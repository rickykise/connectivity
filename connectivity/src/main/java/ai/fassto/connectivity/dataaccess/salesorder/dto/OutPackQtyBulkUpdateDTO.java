package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPackEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OutPackQtyBulkUpdateDTO {
    private String whCd;
    private String outOrdSlipNo;
    private String packGodCancelYn;
    private List<OutPackEntity> outPackEntityList;

    @Builder
    public OutPackQtyBulkUpdateDTO(String whCd, String outOrdSlipNo, String packGodCancelYn, List<OutPackEntity> outPackEntityList){
        this.whCd = whCd;
        this.outOrdSlipNo = outOrdSlipNo;
        this.packGodCancelYn = packGodCancelYn;
        this.outPackEntityList = outPackEntityList;
    }
}
