package ai.fassto.connectivity.externalservice.wms.solochain.salesorder.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.wms.SalesOrderWms;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.AsyncThreadPoolComponent;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.dto.SalesOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.dto.SalesOrderWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.mapper.SalesOrderWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.send.rest.SalesOrderFeignClient;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject.Result;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE;
import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.AsyncThreadPoolConfiguration.ASYNC_JOB_EXECUTOR;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_SALES_ORDER;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.isSuccessResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class SalesOrderWmsSoloChainImpl implements SalesOrderWms {

    private final SalesOrderWmsSoloChainMapper mapper;
    private final SalesOrderFeignClient salesorderFeignClient;
    private final ConnectivityPropertyService propertyService;
    private final AsyncThreadPoolComponent asyncThreadPoolComponent;

    @Value("${wms.api.sales-order}")
    private String salesOrderApiUrl;

    @Override
    public SalesOrder create(SalesOrder salesorder) {
        return callSalesOrderApi(salesorder);
    }

    @Override
    public SalesOrder update(SalesOrder salesorder) {
        return callSalesOrderApi(salesorder);
    }

    @Override
    public SalesOrder delete(SalesOrder salesorder) {
        return callSalesOrderApi(salesorder);
    }

    @Override
    @Async(ASYNC_JOB_EXECUTOR)
    public void createAsAsync(List<SalesOrder> salesOrders) {
        callSalesOrdersApiBy(salesOrders);
    }

    @Override
    @Async(ASYNC_JOB_EXECUTOR)
    public void updateAsAsync(List<SalesOrder> salesOrders) {
        callSalesOrdersApiBy(salesOrders);
    }

    @Override
    @Async(ASYNC_JOB_EXECUTOR)
    public void deleteAsAsync(List<SalesOrder> salesOrders) {
        callSalesOrdersApiBy(salesOrders);
    }


    private List<Result> callSalesOrdersApiBy(List<SalesOrder> salesorders) {
        List<CompletableFuture<Result>> futures = new ArrayList<>();
        final long requestAtAsTimestamp = System.currentTimeMillis();
        TaskExecutor taskExecutor = asyncThreadPoolComponent.getTaskExecutor();
        log.info("[START-all] salesOrderListSize: {}", salesorders.size());

        int index = 0;
        for (SalesOrder salesorder : salesorders) {
            final int i = ++index;

            futures.add(CompletableFuture.supplyAsync(() -> {
                final long subRequestAtAsTimestamp = System.currentTimeMillis();
                log.info("[START-{}][slipNo: {}, ordNo: {}]", i, salesorder.getId().getValue(), salesorder.getOrderNo());

                try {
                    callSalesOrderApi(salesorder);
                    log.info("[END-{}][slipNo: {}, ordNo: {}] elapsedTime: {} ms",
                            i, salesorder.getId().getValue(), salesorder.getOrderNo(), getElapsedTimeInMs(subRequestAtAsTimestamp)
                    );
                    return Result.builder().result("success").salesOrder(salesorder).build();
                } catch (Exception e) {
                    log.info("[END-{}][Error][slipNo: {}, ordNo: {}] elapsedTime: {} ms",
                            i, salesorder.getId().getValue(), salesorder.getOrderNo(), getElapsedTimeInMs(subRequestAtAsTimestamp)
                    );
                    return Result.builder().result("failure").salesOrder(salesorder).build();
                }
            }, taskExecutor));
        }

        CompletableFuture<List<Result>> results = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).toList());

        try {
            List<Result> response = results.get();
            log.info("[END-all] elapsedTime: {} ms, salesOrderReturnListSize: {}", getElapsedTimeInMs(requestAtAsTimestamp), response.size());
            return response;
        } catch (Exception e) {
            log.info("[END-all][Error] elapsedTime: {} ms, error: {}", getElapsedTimeInMs(requestAtAsTimestamp), e.getStackTrace());
            throw new ExternalApiCallException(SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE, e);
        }
    }

    private SalesOrder callSalesOrderApi(SalesOrder salesorder) {
        SalesOrderWmsSoloChainResponse response = null;
        final long requestAtAsTimestamp = System.currentTimeMillis();
        URI uri = URI.create(propertyService.getSolochainHostUrl(salesorder.getWarehouseId().getValue()));
        SalesOrderWmsSoloChainRequest soloParams = mapper.salesOrderToCreateUpdateSalesOrderWmsSoloChainRequest(salesorder);
        log.info("[slipNo: {}, ordNo: {}] url: {}, soloParams: {}", salesorder.getId().getValue(), salesorder.getOrderNo(), uri + salesOrderApiUrl, toJson(soloParams));

        try {
            response = salesorderFeignClient.updateSalesOrder(
                    uri, toHeaders(propertyService.getApiKey(salesorder.getWarehouseId().getValue())), soloParams
            );
            log.info("[slipNo: {}, ordNo: {}] elapsedTime: {} ms, response: {}", salesorder.getId().getValue(), salesorder.getOrderNo(), getElapsedTimeInMs(requestAtAsTimestamp), toJson(response));
        } catch (FeignException exception) {
            log.info("[slipNo: {}, ordNo: {}] RETRY_ONCE elapsedTime: {} ms, error: {}", salesorder.getId().getValue(), salesorder.getOrderNo(), getElapsedTimeInMs(requestAtAsTimestamp), exception.getMessage());
            try {
                response = salesorderFeignClient.updateSalesOrder(
                        uri, toHeaders(propertyService.getApiKey(salesorder.getWarehouseId().getValue())), soloParams
                );
                log.info("[slipNo: {}, ordNo: {}] RETRY_NO_MORE elapsedTime: {} ms,  response: {}", salesorder.getId().getValue(), salesorder.getOrderNo(), getElapsedTimeInMs(requestAtAsTimestamp), toJson(response));
            } catch (FeignException exception1) {
                log.info("[slipNo: {}, ordNo: {}] RETRY_NO_MORE elapsedTime: {} ms, error: {}", salesorder.getId().getValue(), salesorder.getOrderNo(), getElapsedTimeInMs(requestAtAsTimestamp), exception1.getMessage());
                FeignExceptionHandler.externalApiCallExceptionHandler(exception1);
            }
        }

        if (response == null) {
            log.info("[slipNo: {}, ordNo: {}]  error: response body empty", salesorder.getId().getValue(), salesorder.getOrderNo());
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_SALES_ORDER, "API Call Result response is null")));
        } else if (!isSuccessResponse(response.getResult())) {
            log.info("[slipNo: {}, ordNo: {}]  error: {}", salesorder.getId().getValue(), salesorder.getOrderNo(), response.getMessages());
            FeignExceptionHandler.SalesOrderExceptionHandler(response.getMessages());
        }

        return salesorder;
    }

    private int getElapsedTimeInMs(long requestAtAsTimestamp) {
        return (int) (System.currentTimeMillis() - requestAtAsTimestamp);
    }
}
