package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutInventoryAssignEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class OutInventoryAssignLotNoBulkUpdateDTO {
    private String outOrdSlipNo;
    private String whCd;
    private List<OutInventoryAssignEntity> outInventoryAssignEntityList;
}
