package ai.fassto.connectivity.domain.stock.application;

import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockRequest;
import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockResponse;
import ai.fassto.connectivity.domain.stock.application.port.input.service.StockApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockApplicationServiceImpl implements StockApplicationService {
    private final StockUpdateRequestHandler stockUpdateRequestHandler;


    @Override
    public UpdateErpStockResponse update(UpdateErpStockRequest request) {
        return stockUpdateRequestHandler.updateWarehouseInventory(request);
    }
}
