package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.ItemEntity;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OutPickOrdBulkUpdateDTO {

    private String whCd;

    private final int pickSeq = 1;

    private String wrkStat;
    private String outOrdSlipNo;
    private String cstCd;
    private String shopCd;

    private List<ItemEntity> itemList;

    @Builder
    public OutPickOrdBulkUpdateDTO(String whCd, String wrkStat, String outOrdSlipNo, String cstCd, String shopCd,
        List<ItemEntity> itemList) {
        this.whCd = whCd;
        this.wrkStat = wrkStat;
        this.outOrdSlipNo = outOrdSlipNo;
        this.cstCd = cstCd;
        this.shopCd = shopCd;
        this.itemList = itemList;
    }
}
