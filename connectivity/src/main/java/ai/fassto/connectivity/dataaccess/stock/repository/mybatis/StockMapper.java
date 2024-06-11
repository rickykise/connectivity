package ai.fassto.connectivity.dataaccess.stock.repository.mybatis;

import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryEntity;
import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
    int insertWarehouseInventoryOnDuplicateKeyUpdate(List<WarehouseInventoryEntity> warehouseInventoryEntityList);
    int insertWarehouseInventoryHistory(List<WarehouseInventoryHistoryEntity> warehouseInventoryHistoryEntityList);
}
