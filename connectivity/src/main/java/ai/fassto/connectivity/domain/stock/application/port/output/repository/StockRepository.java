package ai.fassto.connectivity.domain.stock.application.port.output.repository;

import ai.fassto.connectivity.domain.stock.core.entity.Stock;

import java.util.List;

public interface StockRepository {
    void updateInventoryAndInsertHistory(List<Stock> stockList);
}
