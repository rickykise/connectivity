package ai.fassto.connectivity.externalservice.partnerApi.mapper;

import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryPartnerApiEntity;
import ai.fassto.connectivity.externalservice.partnerApi.dto.PartnerApiPromotionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PartnerApiMapper {
    public List<PartnerApiPromotionRequest> stockTopartnerApiPromotionRequestList(List<WarehouseInventoryPartnerApiEntity> WarehouseInventoryPartnerApiEntityList) {
        List<PartnerApiPromotionRequest> PartnerApiPromotionRequestList = new ArrayList<>();

        for (WarehouseInventoryPartnerApiEntity warehouseInventoryPartnerApiEntity : WarehouseInventoryPartnerApiEntityList) {
            PartnerApiPromotionRequest request = PartnerApiPromotionRequest.builder()
                    .userId("CONNECTIVITY")
                    .godCd(warehouseInventoryPartnerApiEntity.getGodCd())
                    .quantity(warehouseInventoryPartnerApiEntity.getStockQty())
                    .build();

            PartnerApiPromotionRequestList.add(request);
        }

        return PartnerApiPromotionRequestList;
    }

}
