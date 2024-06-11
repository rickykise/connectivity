package ai.fassto.connectivity.dataaccess.stock.adapter;

import ai.fassto.connectivity.dataaccess.stock.mapper.StockDataAccessMapper;
import ai.fassto.connectivity.dataaccess.stock.repository.mybatis.StockMapper;
import ai.fassto.connectivity.domain.stock.application.port.output.external.api.partnerApi.StockPartnerApi;
import ai.fassto.connectivity.domain.stock.application.port.output.repository.StockRepository;
import ai.fassto.connectivity.domain.stock.core.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {
    private final StockMapper stockMapper;
    private final StockDataAccessMapper stockDataAccessMapper;
    private final StockPartnerApi stockPartnerApi;

    @Transactional
    @Override
    public void updateInventoryAndInsertHistory(List<Stock> stockList) {
        stockMapper.insertWarehouseInventoryHistory(stockDataAccessMapper.stockListToWarehouseInventoryHistoryEntityList(stockList));
        stockMapper.insertWarehouseInventoryOnDuplicateKeyUpdate(stockDataAccessMapper.stockListToWarehouseInventoryEntityList(stockList));
    }

}
