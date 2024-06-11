package ai.fassto.connectivity.application.rest.purchaseorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.port.input.service.PurchaseOrderApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderResponse.getUpdatePurchaseOrderResponseMessageBy;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/v1/purchase-order")
@Api(tags = {"Purchase Order APIs to ERP"})
public class ErpPurchaseOrderApiController {
    private final PurchaseOrderApplicationService service;

    @PutMapping
    @ApiOperation(value = "Change of purchase order status (입고주문상태 변경)", notes = "Change of purchase order status")
    public GenericResponse<UpdateErpPurchaseOrderResponse> updatePurchaseOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            UpdateErpPurchaseOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/erp/v1/purchase-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdatePurchaseOrderResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }
}
