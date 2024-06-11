package ai.fassto.connectivity.application.rest.workorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.port.input.service.WorkOrderApplicationService;
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
import static ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1/work-order")
@Api(tags = {"Work Order APIs to Solochain"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class SoloChainWorkOrderApiController {
    private final WorkOrderApplicationService service;

    @PostMapping
    @ApiOperation(value = "Create Work Order  (유통가공 주문 생성)", notes = "Create Work Order")
    public GenericResponse<SoloChainWorkOrderResponse> createWorkOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainWorkOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/work-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping
    @ApiOperation(value = "Update Work Order  (유통가공 주문 수정)", notes = "Update Work Order")
    public GenericResponse<SoloChainWorkOrderResponse> updateWorkOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainWorkOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/work-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }

    @PatchMapping
    @ApiOperation(value = "Delete Work Order  (유통가공 주문 삭제)", notes = "Delete Work Order")
    public GenericResponse<SoloChainWorkOrderResponse> deleteWorkOrder(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainWorkOrderRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PATCH /api/solochain/v1/work-order, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getDeleteResponseMessageBy(servletRequest),
                service.delete(request),
                OK
        );
    }
}
