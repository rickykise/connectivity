package ai.fassto.connectivity.domain.salesorder.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersResponse;
import ai.fassto.connectivity.domain.salesorder.application.mapper.SolochainSalesOrderDataMapper;
import ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.wms.SalesOrderWms;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderCreateRequestHandler {
    private final SolochainSalesOrderDataMapper solochainSalesOrderDataMapper;
    private final SalesOrderWms salesOrderWms;

    public SoloChainSalesOrderResponse createSalesOrder(SoloChainSalesOrderRequest request) {
        return solochainSalesOrderDataMapper.salesOrderToSoloChainSalesOrderResponse(
                salesOrderWms.create(
                        solochainSalesOrderDataMapper.soloChainSalesOrderRequestToSalesOrder(request, ActionType.CREATE)
                )
        );
    }

    public SoloChainSalesOrdersResponse createSalesOrders(SoloChainSalesOrdersRequest request) {
        List<SalesOrder> salesOrders = solochainSalesOrderDataMapper.soloChainSalesOrdersRequestToSalesOrderList(request, ActionType.CREATE);
        salesOrderWms.createAsAsync(salesOrders);

        return solochainSalesOrderDataMapper.salesOrderToSoloChainSalesOrdersResponse(solochainSalesOrderDataMapper.salesOrdersToResult(salesOrders));
    }
}
