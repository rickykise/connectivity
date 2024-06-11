package ai.fassto.connectivity.domain.stock.application.mapper;

import ai.fassto.connectivity.domain.stock.application.dto.erp.update.Item;
import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockRequest;
import ai.fassto.connectivity.domain.stock.core.entity.Stock;
import ai.fassto.connectivity.dataaccess.common.valueobject.InventoryCondition;
import ai.fassto.connectivity.domain.stock.core.valueobject.StockId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockDataMapper {
    private final static String DEFAULT_LU_NO = "NONE";
    private final static String DEFAULT_LOT_NO = "N/A";

    public List<Stock> updateErpStockRequestToStock(UpdateErpStockRequest request) {
        return request.getItems().stream().map(this::toStock).toList();
    }

    private Stock toStock(Item item) {
        return Stock.Builder.builder()
                .id(StockId.builder()
                        .whCd(item.getWarehouseCode())
                        .luNo(DEFAULT_LU_NO)
                        .lotNo(StringUtils.isBlank(item.getLotNo()) ? DEFAULT_LOT_NO : item.getLotNo())
                        .godCd(item.getGodCd())
                        .condition(InventoryCondition.findBySolochainCode(item.getCondition()))
                        .build()
                )
                .changeQuantity(item.getStockQty())
                .expiryDate(item.getExpiryDate() == null ? null : item.getExpiryDate().toLocalDate())
                .makeDate(item.getMakeDate() == null ? null : item.getMakeDate().toLocalDate())
                .adjustmentReasonCode(item.getAdjustmentReasonCode())
                .type(item.getType())
                .build();
    }
}
