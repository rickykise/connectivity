package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutOrdSetEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OutOrdSetBulkInsertDTO {

    private String ordDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String shopCd;
    private String ordNo;
    private Integer ordSeq;
    private String ordCancelYn;

    private List<OutOrdSetEntity> outOrdSetEntityList;

    public OutOrdSetEntity toOutOrdSetEntity(){
        return OutOrdSetEntity.builder()
            .ordDt(this.ordDt)
            .whCd(this.whCd)
            .slipNo(this.slipNo)
            .cstCd(this.cstCd)
            .shopCd(this.shopCd)
            .ordNo(this.ordNo)
            .ordSeq(this.ordSeq)
            .ordCancelYn(this.ordCancelYn)
            .build();
    }

}
