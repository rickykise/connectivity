package ai.fassto.connectivity.externalservice.partnerApi.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryPartnerApiEntity;
import ai.fassto.connectivity.domain.stock.application.port.output.external.api.partnerApi.StockPartnerApi;
import ai.fassto.connectivity.externalservice.partnerApi.dto.PartnerApiPromotionRequest;
import ai.fassto.connectivity.externalservice.partnerApi.dto.PartnerApiPromotionResponse;
import ai.fassto.connectivity.externalservice.partnerApi.mapper.PartnerApiMapper;
import ai.fassto.connectivity.externalservice.partnerApi.send.rest.PartnerApiFeignClient;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.STOCK;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockPartnerApiImpl implements StockPartnerApi {

    private final PartnerApiMapper mapper;
    private final PartnerApiFeignClient partnerApiFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${partner-api.api.create-promotion}")
    private String partnerApiUrl;


    @Override
    public void create(List<WarehouseInventoryPartnerApiEntity> warehouseInventoryPartnerApiEntityList) {

        List<PartnerApiPromotionRequest> partnerApiPromotionRequestList = mapper.stockTopartnerApiPromotionRequestList(warehouseInventoryPartnerApiEntityList);
        for (PartnerApiPromotionRequest request : partnerApiPromotionRequestList) {
            callPartnerApiPromotion(request);
        }

    }

    private void callPartnerApiPromotion(PartnerApiPromotionRequest request) {
        PartnerApiPromotionResponse response = null;

        try {
            URI uri = URI.create(propertyService.getPartnerApiHostUrl());

            log.info("url: {}, partnerApiParams: {}", uri + partnerApiUrl, toJson(request));

            response = partnerApiFeignClient.createPormotion(
                    uri,
                    toHeaders(),
                    request
            );
        } catch (FeignException exception) {
            FeignExceptionHandler.externalApiCallExceptionHandler(exception);
        }

        if (response == null) {
            ai.fassto.connectivity.externalservice.carrier.common.exception.handler.FeignExceptionHandler.tmsExternalApiCallExceptionHandler(List.of(new ErrorDetail(STOCK, "OMS Partner API Call Result response is null")));
        }


    }

}
