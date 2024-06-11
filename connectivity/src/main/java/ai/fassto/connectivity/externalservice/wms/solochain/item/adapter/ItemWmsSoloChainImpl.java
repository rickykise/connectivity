package ai.fassto.connectivity.externalservice.wms.solochain.item.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.item.application.port.output.external.api.wms.ItemWms;
import ai.fassto.connectivity.domain.item.core.entity.Item;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.AsyncThreadPoolComponent;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.ItemWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.ItemWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.item.mapper.ItemWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.item.send.rest.ItemFeignClient;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.Result;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE;
import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.AsyncThreadPoolConfiguration.ASYNC_JOB_EXECUTOR;

@Component
@Slf4j
@RequiredArgsConstructor
public class ItemWmsSoloChainImpl implements ItemWms {

    private final ItemWmsSoloChainMapper mapper;
    private final ItemFeignClient itemFeignClient;
    private final ConnectivityPropertyService propertyService;
    private final AsyncThreadPoolComponent asyncThreadPoolComponent;

    @Value("${wms.api.item}")
    private String itemApiUrl;

    @Override
    public List<Result> create(Item item) {
        return callItemApiBy(item);
    }

    @Override
    public List<Result> update(Item item) {
        return callItemApiBy(item);
    }

    @Override
    public List<Result> delete(Item item) {
        return callItemApiBy(item);
    }

    @Override
    @Async(ASYNC_JOB_EXECUTOR)
    public void createAsAsync(List<Item> itemList) {
        callItemsApiBy(itemList);
    }

    @Override
    @Async(ASYNC_JOB_EXECUTOR)
    public void updateAsAsync(List<Item> itemList) {
        callItemsApiBy(itemList);
    }

    @Override
    @Async(ASYNC_JOB_EXECUTOR)
    public void deleteAsAsync(List<Item> itemList) {
        callItemsApiBy(itemList);
    }

    private List<Result> callItemApiBy(Item item) {
        List<CompletableFuture<Result>> futures = new ArrayList<>();
        final long requestAtAsTimestamp = System.currentTimeMillis();

        log.info("[START][godCd:{}]", item.getId().getValue());
        try {
            callItemApi(item);

            log.info("[END][godCd:{}] elapsedTime: {} ms", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp));
            return List.of(Result.builder().result("success").godCd(item.getId().getValue()).godNm(item.getName()).build());
        } catch (Exception e) {
            log.info("[solochain Error][END][godCd:{}] elapsedTime: {} ms", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp));
            return List.of(Result.builder().result("failure").godCd(item.getId().getValue()).godNm(item.getName()).build());
        }
    }


    private List<Result> callItemsApiBy(List<Item> itemList) {
        List<CompletableFuture<Result>> futures = new ArrayList<>();
        final long requestAtAsTimestamp = System.currentTimeMillis();
        TaskExecutor taskExecutor = asyncThreadPoolComponent.getTaskExecutor();
        log.info("[START-all] itemListSize: {}", itemList.size());

        int index = 0;
        for (Item item : itemList) {
            final int i = ++index;

            futures.add(CompletableFuture.supplyAsync(() -> {
                final long subRequestAtAsTimestamp = System.currentTimeMillis();
                log.info("[START-{}][godCd:{}]", i, item.getId().getValue());
                try {
                    callItemApi(item);

                    log.info("[END-{}][godCd:{}] elapsedTime: {} ms", i, item.getId().getValue(), getElapsedTimeInMs(subRequestAtAsTimestamp));
                    return Result.builder().result("success").godCd(item.getId().getValue()).godNm(item.getName()).build();
                } catch (Exception e) {
                    log.info("[END-{}][Error][godCd:{}] elapsedTime: {} ms", i, item.getId().getValue(), getElapsedTimeInMs(subRequestAtAsTimestamp));
                    return Result.builder().result("failure").godCd(item.getId().getValue()).godNm(item.getName()).build();
                }
            }, taskExecutor));
        }

        CompletableFuture<List<Result>> results = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).toList());

        try {
            List<Result> response = results.get();
            log.info("[END-all] elapsedTime: {} ms, itemReturnListSize: {}", getElapsedTimeInMs(requestAtAsTimestamp), response.size());
            return response;
        } catch (Exception e) {
            log.info("[END-all][Error] elapsedTime: {} ms, error: {}", getElapsedTimeInMs(requestAtAsTimestamp), e.getStackTrace());
            throw new ExternalApiCallException(SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE, e);
        }
    }

    private int getElapsedTimeInMs(long requestAtAsTimestamp) {
        return (int) (System.currentTimeMillis() - requestAtAsTimestamp);
    }

    private Item callItemApi(Item item) {
        ItemWmsSoloChainResponse response = null;
        final long requestAtAsTimestamp = System.currentTimeMillis();
        URI uri = URI.create(propertyService.getSolochainHostUrl(item.getWarehouseId().getValue()));
        ItemWmsSoloChainRequest soloParams = mapper.itemToCreateUpdateItemWmsSoloChainRequest(Collections.singletonList(item));
        log.info("[godCd: {}] url: {}, soloParams: {}", item.getId().getValue(), uri + itemApiUrl, toJson(soloParams));

        try {
            response = itemFeignClient.updateItem(
                    uri, toHeaders(propertyService.getApiKey(item.getWarehouseId().getValue())), soloParams
            );
            log.info("[godCd: {}] elapsedTime: {} ms, response: {}", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), toJson(response));
        } catch (FeignException exception) {
            log.error("[godCd: {}] RETRY_ONCE error: {}", item.getId().getValue(), exception.getMessage());
            try {
                response = itemFeignClient.updateItem(
                        uri, toHeaders(propertyService.getApiKey(item.getWarehouseId().getValue())), soloParams
                );
                log.info("[godCd: {}] RETRY_NO_MORE elapsedTime: {} ms, response: {}", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), toJson(response));
            } catch (FeignException exception1) {
                log.error("[godCd: {}] RETRY_NO_MORE elapsedTime: {} ms, error: {}", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), exception1.getMessage());
                FeignExceptionHandler.externalApiCallExceptionHandler(exception1);
            }
        }
        if (response == null) {
            log.info("[godCd: {}] elapsedTime: {} ms, error: response body empty", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp));
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(SoloChainConstant.DOMAIN_ITEM, "API Call Result response is null")));
        } else if (!SoloChainConstant.isSuccessResponse(response.getResult())) {
            log.error("[godCd: {}] elapsedTime: {} ms, error: {}", item.getId().getValue(), getElapsedTimeInMs(requestAtAsTimestamp), response.getMessages());
            FeignExceptionHandler.itemExceptionHandler(response.getMessages());
        }

        return item;
    }
}
