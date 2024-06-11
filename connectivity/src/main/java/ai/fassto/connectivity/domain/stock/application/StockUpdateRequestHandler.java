package ai.fassto.connectivity.domain.stock.application;

import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockRequest;
import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockResponse;
import ai.fassto.connectivity.domain.stock.application.mapper.StockDataMapper;
import ai.fassto.connectivity.domain.stock.application.port.output.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class StockUpdateRequestHandler {
    private final StockDataMapper stockDataMapper;
    private final StockRepository stockRepository;

    public UpdateErpStockResponse updateWarehouseInventory(UpdateErpStockRequest request) {
        stockRepository.updateInventoryAndInsertHistory(stockDataMapper.updateErpStockRequestToStock(request));
        return new UpdateErpStockResponse();
    }

}
