package ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.external.api.wms.PurchaseOrderWms;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.dto.PurchaseOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.dto.PurchaseOrderWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.mapper.PurchaseOrderWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.purchaseorder.send.rest.PurchaseOrderFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.PurchaseOrderType.PURCHASE_ORDER;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseOrderWmsSoloChainImpl implements PurchaseOrderWms {

    private final PurchaseOrderWmsSoloChainMapper mapper;
    private final PurchaseOrderFeignClient purchaseOrderFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${wms.api.purchase-order}")
    private String purchaseOrderApiUrl;

    @Value("${wms.api.return-order}")
    private String returnOrderApiUrl;


    @Override
    public PurchaseOrder create(PurchaseOrder purchaseorder) {
        return callPurchaseOrderApi(purchaseorder);
    }

    @Override
    public PurchaseOrder update(PurchaseOrder purchaseorder) {
        return callPurchaseOrderApi(purchaseorder);
    }

    @Override
    public PurchaseOrder delete(PurchaseOrder purchaseorder) {
        return callPurchaseOrderApi(purchaseorder);
    }

    private PurchaseOrder callPurchaseOrderApi(PurchaseOrder purchaseorder) {
        PurchaseOrderWmsSoloChainResponse response = null;
        final long requestAtAsTimestamp = System.currentTimeMillis();
        URI uri = URI.create(propertyService.getSolochainHostUrl(purchaseorder.getWarehouseId().getValue()));
        PurchaseOrderWmsSoloChainRequest soloParams = mapper.purchaseOrderToPurchaseOrderWmsSoloChainRequest(purchaseorder);

        if (purchaseorder.getOrderLineList().size() < 2) {
            log.info("[START][slipNo: {}] url: {}, soloParams: {}", purchaseorder.getId().getValue(), uri + purchaseOrderApiUrl, toJson(soloParams));
        } else {
            log.info("[START][slipNo: {}] itemSize: {}", purchaseorder.getId().getValue(), purchaseorder.getOrderLineList().size());
        }

        try {
            if (PURCHASE_ORDER.equals(purchaseorder.getType())) {
                response = purchaseOrderFeignClient.updatePurchaseOrder(uri, toHeaders(propertyService.getApiKey(purchaseorder.getWarehouseId().getValue())), soloParams);
            } else { // CONSUMER_RETURN_ORDER || CENTER_RETURN_ORDER
                response = purchaseOrderFeignClient.updateReturnOrder(uri, toHeaders(propertyService.getApiKey(purchaseorder.getWarehouseId().getValue())), soloParams);
            }
            log.info("[END][slipNo: {}] elapsedTime: {} ms, response: {}", purchaseorder.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), toJson(response));
        } catch (FeignException exception) {
            log.info("[END][slipNo: {}] RETRY_ONCE elapsedTime: {} ms, error: {}", purchaseorder.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), exception.getMessage());

            try {
                if (PURCHASE_ORDER.equals(purchaseorder.getType())) {
                    response = purchaseOrderFeignClient.updatePurchaseOrder(uri, toHeaders(propertyService.getApiKey(purchaseorder.getWarehouseId().getValue())), soloParams);
                } else { // CONSUMER_RETURN_ORDER || CENTER_RETURN_ORDER
                    response = purchaseOrderFeignClient.updateReturnOrder(uri, toHeaders(propertyService.getApiKey(purchaseorder.getWarehouseId().getValue())), soloParams);
                }
                log.info("[END][slipNo: {}] RETRY_NO_MORE elapsedTime: {} ms, response: {}", purchaseorder.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), toJson(response));
            } catch (FeignException exception1) {
                log.info("[END][Error][slipNo: {}] RETRY_NO_MORE elapsedTime: {} ms, error: {}", purchaseorder.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), exception1.getMessage());
                FeignExceptionHandler.externalApiCallExceptionHandler(exception1);
            }
        }
        if (response == null) {
            log.info("[END][slipNo: {}] elapsedTime: {} ms, error: response body empty", purchaseorder.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp));
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(SoloChainConstant.DOMAIN_PURCHASE_ORDER, "API Call Result response is null")));
        } else if (!SoloChainConstant.isSuccessResponse(response.getResult())) {
            log.info("[END][slipNo: {}] elapsedTime: {} ms, error: {}", purchaseorder.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), response.getMessages());
            FeignExceptionHandler.PurchaseOrderExceptionHandler(response.getMessages());
        }

        return purchaseorder;
    }

    private int getElapsedTimeInMs(long requestAtAsTimestamp) {
        return (int) (System.currentTimeMillis() - requestAtAsTimestamp);
    }
}
