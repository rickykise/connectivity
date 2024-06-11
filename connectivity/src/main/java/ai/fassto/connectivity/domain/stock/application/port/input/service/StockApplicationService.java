package ai.fassto.connectivity.domain.stock.application.port.input.service;


import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockRequest;
import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockResponse;

public interface StockApplicationService {

    UpdateErpStockResponse update(UpdateErpStockRequest request);
}
