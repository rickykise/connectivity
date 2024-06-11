package ai.fassto.connectivity.domain.salesorder.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersResponse;
import ai.fassto.connectivity.domain.salesorder.application.port.input.service.SalesOrderApplicationService;
import ai.fassto.connectivity.domain.salesorder.core.exception.SalesOrderNotSupportException;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesOrderApplicationServiceImpl implements SalesOrderApplicationService {
    private final SalesOrderCreateRequestHandler salesOrderCreateRequestHandler;
    private final SalesOrderUpdateRequestHandler salesOrderUpdateRequestHandler;
    private final SalesOrderDeleteRequestHandler salesOrderDeleteRequestHandler;
    private final PreCallWhenAllocatedHandler preCallWhenAllocatedHandler;
    private final OmsSmartStoreWhenAllocatedHandler omsSmartStoreWhenAllocatedHandler;


    @Override
    public SoloChainSalesOrderResponse create(SoloChainSalesOrderRequest request) {
        return salesOrderCreateRequestHandler.createSalesOrder(request);
    }

    @Override
    public SoloChainSalesOrderResponse update(SoloChainSalesOrderRequest request) {
        return salesOrderUpdateRequestHandler.updateSalesOrder(request);
    }

    @Override
    public SoloChainSalesOrderResponse delete(SoloChainSalesOrderRequest request) {
        return salesOrderDeleteRequestHandler.deleteSalesOrder(request);
    }

    @Override
    public SoloChainSalesOrdersResponse create(SoloChainSalesOrdersRequest request) {
        return salesOrderCreateRequestHandler.createSalesOrders(request);
    }

    @Override
    public SoloChainSalesOrdersResponse update(SoloChainSalesOrdersRequest request) {
        return salesOrderUpdateRequestHandler.updateSalesOrders(request);
    }

    @Override
    public SoloChainSalesOrdersResponse delete(SoloChainSalesOrdersRequest request) {
        return salesOrderDeleteRequestHandler.deleteSalesOrders(request);
    }

    @Override
    public UpdateErpSalesOrderResponse update(UpdateErpSalesOrderRequest request) {
        SalesOrderStatus status = SalesOrderStatus.findBySolochainStatus(request.getOrderStatus());
        if (status.equals(ALLOCATED)) {
            UpdateErpSalesOrderResponse allocated = salesOrderUpdateRequestHandler.allocate(request);
            TmsPreCallParcelResponse tmsPreCallParcelResponse = preCallWhenAllocatedHandler.sendPreCall(request);
            omsSmartStoreWhenAllocatedHandler.registerInvoiceWhenOmsSmartStore(allocated, tmsPreCallParcelResponse);

            return allocated;
        } else if (status.equals(PICKED)) {
            return salesOrderUpdateRequestHandler.completePicking(request);
        } else if (status.equals(PACKED)) {
            return salesOrderUpdateRequestHandler.completePacking(request);
        } else if (status.equals(SHIPPED)) {
            return salesOrderUpdateRequestHandler.completeShipping(request);
        } else if (status.equals(OUTSTANDING)) {
            return salesOrderUpdateRequestHandler.cancelAllocate(request);
        } else {
            throw new SalesOrderNotSupportException(new ErrorDetail("SalesOrderStatus", status.name()));
        }
    }
}
