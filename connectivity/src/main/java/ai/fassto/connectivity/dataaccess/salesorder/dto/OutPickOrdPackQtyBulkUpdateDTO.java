package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPickOrdEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class OutPickOrdPackQtyBulkUpdateDTO {
    private String whCd;
    private String cstCd;
    private String outOrdSlipNo;
    private List<OutPickOrdEntity> outPickOrdEntityList;
}
