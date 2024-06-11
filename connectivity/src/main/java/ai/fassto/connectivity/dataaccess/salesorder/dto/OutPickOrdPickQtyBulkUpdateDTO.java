package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPickOrdEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class OutPickOrdPickQtyBulkUpdateDTO {
    private String whCd;
    private String outOrdSlipNo;
    private String cstCd;
    private List<OutPickOrdEntity> outPickOrdEntityList;
}
