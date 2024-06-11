package ai.fassto.connectivity.dataaccess.stock.mapper;

import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryEntity;
import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryHistoryEntity;
import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryPartnerApiEntity;
import ai.fassto.connectivity.domain.stock.core.entity.Stock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyyMMdd;

@Component
public class StockDataAccessMapper {
    private static final String CONNECTIVITY = "CONNECTIVITY";
    public List<WarehouseInventoryEntity> stockListToWarehouseInventoryEntityList(List<Stock> stockList) {
        return stockList.stream().map(this::toWarehouseInventoryEntity).toList();
    }

    public List<WarehouseInventoryHistoryEntity> stockListToWarehouseInventoryHistoryEntityList(List<Stock> stockList) {
        return stockList.stream().map(this::toWarehouseInventoryHistoryEntity).toList();
    }

    public List<WarehouseInventoryPartnerApiEntity> stockListToWarehouseInventoryPartnerApiEntityList(List<Stock> stockList) {
        return stockList.stream().map(this::toWarehouseInventoryPartnerApiEntity).toList();
    }

    private WarehouseInventoryHistoryEntity toWarehouseInventoryHistoryEntity(Stock stock) {
        return WarehouseInventoryHistoryEntity.builder()
                .whCd(stock.getId().getWhCd())
                .luNo(stock.getId().getLuNo())
                .lotNo(stock.getId().getLotNo())
                .godCd(stock.getId().getGodCd())
                .conditionCd(stock.getId().getCondition().getErpCode())
                .makeDt(stock.getMakeDate() == null ? null : stock.getMakeDate().format(DATE_FORMATTER_yyyyMMdd))
                .distTermDt(stock.getExpiryDate() == null ? null : stock.getExpiryDate().format(DATE_FORMATTER_yyyyMMdd))
                .stockQty(stock.getChangeQuantity())
                .adjustmentReasonCode(stock.getAdjustmentReasonCode())
                .type(stock.getType())
                .regTime(LocalDateTime.now())
                .build();
    }

    private WarehouseInventoryEntity toWarehouseInventoryEntity(Stock stock) {
        return WarehouseInventoryEntity.builder()
                .whCd(stock.getId().getWhCd())
                .luNo(stock.getId().getLuNo())
                .lotNo(stock.getId().getLotNo())
                .godCd(stock.getId().getGodCd())
                .conditionCd(stock.getId().getCondition().getErpCode())
                .makeDt(stock.getMakeDate() == null ? null : stock.getMakeDate().format(DATE_FORMATTER_yyyyMMdd))
                .distTermDt(stock.getExpiryDate() == null ? null : stock.getExpiryDate().format(DATE_FORMATTER_yyyyMMdd))
                .stockQty(stock.getChangeQuantity())
                .regId(CONNECTIVITY)
                .regTime(LocalDateTime.now())
                .updId(CONNECTIVITY)
                .updTime(LocalDateTime.now())
                .build();
    }

    private WarehouseInventoryPartnerApiEntity toWarehouseInventoryPartnerApiEntity(Stock stock) {
        return WarehouseInventoryPartnerApiEntity.builder()
                .godCd(stock.getId().getGodCd())
                .stockQty(stock.getChangeQuantity())
                .conditionCd(stock.getId().getCondition().getSolochainCode())
                .build();
    }
}
