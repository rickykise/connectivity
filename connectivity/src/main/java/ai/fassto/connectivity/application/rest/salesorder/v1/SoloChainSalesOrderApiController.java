package ai.fassto.connectivity.application.rest.salesorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersResponse;
import ai.fassto.connectivity.domain.salesorder.application.port.input.service.SalesOrderApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderResponse.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1")
@Api(tags = {"Sales Order APIs to Solochain"})
public class SoloChainSalesOrderApiController {
    private final SalesOrderApplicationService service;

    @PostMapping("/sales-order")
    @ApiOperation(value = "Create Sales Order  (출고 주문 생성)", notes = "Create Sales Order")
    public GenericResponse<SoloChainSalesOrderResponse> createSalesOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainSalesOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/sales-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping("/sales-order")
    @ApiOperation(value = "Update Sales Order  (출고 주문 수정)", notes = "Update Sales Order")
    public GenericResponse<SoloChainSalesOrderResponse> updateSalesOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainSalesOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/sales-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }

    @PatchMapping("/sales-order")
    @ApiOperation(value = "Delete Sales Order  (출고 주문 삭제)", notes = "Delete Sales Order")
    public GenericResponse<SoloChainSalesOrderResponse> deleteSalesOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainSalesOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PATCH /api/solochain/v1/sales-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getDeleteResponseMessageBy(servletRequest),
                service.delete(request),
                OK
        );
    }

    @PostMapping("/sales-orders")
    @ApiOperation(value = "Create Sales Orders  (출고 주문 생성)", notes = "Create Sales Orders")
    public GenericResponse<SoloChainSalesOrdersResponse> createSalesOrders(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainSalesOrdersRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/sales-orders, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping("/sales-orders")
    @ApiOperation(value = "Update Sales Orders  (출고 주문 수정)", notes = "Update Sales Orders")
    public GenericResponse<SoloChainSalesOrdersResponse> updateSalesOrders(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainSalesOrdersRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/sales-orders, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }

    @PatchMapping("/sales-orders")
    @ApiOperation(value = "Delete Sales Orders  (출고 주문 삭제)", notes = "Delete Sales Orders")
    public GenericResponse<SoloChainSalesOrdersResponse> deleteSalesOrders(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainSalesOrdersRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PATCH /api/solochain/v1/sales-orders, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getDeleteResponseMessageBy(servletRequest),
                service.delete(request),
                OK
        );
    }
}
