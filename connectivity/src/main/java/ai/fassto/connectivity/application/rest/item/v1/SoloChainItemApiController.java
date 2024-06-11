package ai.fassto.connectivity.application.rest.item.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemRequest;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemResponse;
import ai.fassto.connectivity.domain.item.application.port.input.service.ItemApplicationService;
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
import static ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemResponse.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1/item")
@Api(tags = {"Item APIs to SOLOCHAIN"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class SoloChainItemApiController {
    private final ItemApplicationService service;

    @PostMapping
    @ApiOperation(value = "Create Item (상품 생성)", notes = "Create Item")
    public GenericResponse<SoloChainItemResponse> createItem(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainItemRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/item, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping
    @ApiOperation(value = "Update Item (상품 수정)", notes = "Update Item")
    public GenericResponse<SoloChainItemResponse> updateItem(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainItemRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/item, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }

    @PatchMapping
    @ApiOperation(value = "Delete Item (상품 삭제)", notes = "Delete Item")
    public GenericResponse<SoloChainItemResponse> deleteItem(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainItemRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PATCH /api/solochain/v1/item, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getDeleteResponseMessageBy(servletRequest),
                service.delete(request),
                OK
        );
    }
}
