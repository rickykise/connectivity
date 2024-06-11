package ai.fassto.connectivity.domain.salesorder.application.port.input.service;


import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersResponse;

public interface SalesOrderApplicationService {

    SoloChainSalesOrderResponse create(SoloChainSalesOrderRequest request);

    SoloChainSalesOrderResponse update(SoloChainSalesOrderRequest request);

    SoloChainSalesOrderResponse delete(SoloChainSalesOrderRequest request);

    SoloChainSalesOrdersResponse create(SoloChainSalesOrdersRequest request);

    SoloChainSalesOrdersResponse update(SoloChainSalesOrdersRequest request);

    SoloChainSalesOrdersResponse delete(SoloChainSalesOrdersRequest request);

    UpdateErpSalesOrderResponse update(UpdateErpSalesOrderRequest request);
}
