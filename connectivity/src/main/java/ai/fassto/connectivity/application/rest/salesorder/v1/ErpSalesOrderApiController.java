package ai.fassto.connectivity.application.rest.salesorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.port.input.service.SalesOrderApplicationService;
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
import static ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse.getUpdateSalesOrderResponseMessageBy;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/v1/sales-order")
@Api(tags = {"Sales Order APIs to ERP"})
public class ErpSalesOrderApiController {
    private final SalesOrderApplicationService service;

    @PutMapping
    public GenericResponse<UpdateErpSalesOrderResponse> updateSalesOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            UpdateErpSalesOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/erp/v1/sales-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateSalesOrderResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }
}
