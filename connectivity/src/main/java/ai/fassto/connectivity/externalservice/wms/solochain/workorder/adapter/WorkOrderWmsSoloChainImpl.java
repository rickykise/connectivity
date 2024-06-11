package ai.fassto.connectivity.externalservice.wms.solochain.workorder.adapter;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import ai.fassto.connectivity.domain.workorder.application.port.output.external.api.wms.WorkOrderWms;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.FeignExceptionHandler;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.dto.WorkOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.dto.WorkOrderWmsSoloChainResponse;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.mapper.WorkOrderWmsSoloChainMapper;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.send.rest.WorkOrderFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

import static ai.fassto.connectivity.common.utility.HttpHeaderUtil.toHeaders;
import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.DOMAIN_WORK_ORDER;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.isSuccessResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class WorkOrderWmsSoloChainImpl implements WorkOrderWms {

    private final WorkOrderWmsSoloChainMapper mapper;
    private final WorkOrderFeignClient workOrderFeignClient;
    private final ConnectivityPropertyService propertyService;

    @Value("${wms.api.work-order}")
    private String workOrderApiUrl;


    @Override
    public WorkOrder create(WorkOrder workOrder) {
        return callWorkOrderApi(workOrder);
    }

    @Override
    public WorkOrder update(WorkOrder workOrder) {
        return callWorkOrderApi(workOrder);
    }

    @Override
    public WorkOrder delete(WorkOrder workOrder) {
        return callWorkOrderApi(workOrder);

    }

    private WorkOrder callWorkOrderApi(WorkOrder workOrder) {
        WorkOrderWmsSoloChainResponse response = null;

        try {
            URI uri = URI.create(propertyService.getSolochainHostUrl(workOrder.getWarehouseId().getValue()));
            WorkOrderWmsSoloChainRequest soloParams = mapper.workOrderToCreateUpdateWorkOrderWmsSoloChainRequest(workOrder);
            log.info("[slipNo: {}] url: {}, soloParams: {}", workOrder.getId().getValue(), uri + workOrderApiUrl, toJson(soloParams));

            response = workOrderFeignClient.updateWorkOrder(
                    uri, toHeaders(propertyService.getApiKey(workOrder.getWarehouseId().getValue())), soloParams
            );
            log.info("[slipNo: {}] response: {}", workOrder.getId().getValue(), toJson(response));
        } catch (FeignException exception) {
            log.info("[slipNo: {}] error: {}", workOrder.getId().getValue(), exception.getMessage());
            FeignExceptionHandler.externalApiCallExceptionHandler(exception);
        }
        if (response == null) {
            log.info("[slipNo: {}] error: response body empty", workOrder.getId().getValue());
            FeignExceptionHandler.externalApiCallExceptionHandler(List.of(new ErrorDetail(DOMAIN_WORK_ORDER, "API Call Result response is null")));
        } else if (!isSuccessResponse(response.getResult())) {
            log.info("[slipNo: {}] error: {}", workOrder.getId().getValue(), response.getMessages());
            FeignExceptionHandler.WorkOrderExceptionHandler(response.getMessages());
        }

        return workOrder;
    }
}
