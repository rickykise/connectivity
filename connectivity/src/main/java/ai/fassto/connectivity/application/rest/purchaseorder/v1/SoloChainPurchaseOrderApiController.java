package ai.fassto.connectivity.application.rest.purchaseorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.PatchSoloChainPurchaseOrderQaOnHoldItemsRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.PatchSoloChainPurchaseOrderQaOnHoldItemsResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.port.input.service.PurchaseOrderApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1/purchase-order")
@Api(tags = {"Purchase Order APIs to Solochain"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class SoloChainPurchaseOrderApiController {
    private final PurchaseOrderApplicationService service;

    @PostMapping
    @ApiOperation(value = "Create Purchase Order  (입고 주문 생성)", notes = "Create Purchase Order")
    public GenericResponse<SoloChainPurchaseOrderResponse> createPurchaseOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainPurchaseOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/purchase-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping
    @ApiOperation(value = "Update Purchase Order  (입고 주문 수정)", notes = "Update Purchase Order")
    public GenericResponse<SoloChainPurchaseOrderResponse> updatePurchaseOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainPurchaseOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/purchase-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }

    @PatchMapping
    @ApiOperation(value = "Delete Purchase Order  (입고 주문 삭제)", notes = "Delete Purchase Order")
    public GenericResponse<SoloChainPurchaseOrderResponse> deletePurchaseOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainPurchaseOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PATCH /api/solochain/v1/purchase-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getDeleteResponseMessageBy(servletRequest),
                service.delete(request),
                OK
        );
    }

    @PatchMapping("/qa-on-hold-items")
    @ApiOperation(value = "QA on hold items confirmation (입고보류확인)", notes = "QA on hold items confirmation (입고보류확인)")
    public GenericResponse<PatchSoloChainPurchaseOrderQaOnHoldItemsResponse> aa(
            @Validated(ValidationSequence.class)
            @RequestBody
            PatchSoloChainPurchaseOrderQaOnHoldItemsRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PATCH /api/solochain/v1/purchase-order/qa-on-hold-items, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                PatchSoloChainPurchaseOrderQaOnHoldItemsResponse.getPatchResponseMessageBy(servletRequest),
                service.confirm(request),
                OK
        );
    }

}
