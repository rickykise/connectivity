package ai.fassto.connectivity.application.rest.workorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.port.input.service.WorkOrderApplicationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderResponse.getUpdateSalesOrderResponseMessageBy;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/v1/work-order")
@Api(tags = {"Work Order APIs to ERP"})
public class ErpWorkOrderApiController {
    private final WorkOrderApplicationService service;

    @PutMapping
    public GenericResponse<UpdateErpWorkOrderResponse> updateWorkOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            UpdateErpWorkOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/erp/v1/work-order, params: {}", toJson(request));

        return new GenericResponse<>(
                getUpdateSalesOrderResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }
}
