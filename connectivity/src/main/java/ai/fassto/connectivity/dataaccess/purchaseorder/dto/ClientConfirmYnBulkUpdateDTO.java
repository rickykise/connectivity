package ai.fassto.connectivity.dataaccess.purchaseorder.dto;

import ai.fassto.connectivity.dataaccess.purchaseorder.entity.InOrdEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ClientConfirmYnBulkUpdateDTO {
    private String whCd;
    private String slipNo;
    private List<InOrdEntity> inOrdEntityList;
}
