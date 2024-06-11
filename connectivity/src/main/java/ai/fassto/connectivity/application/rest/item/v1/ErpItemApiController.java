package ai.fassto.connectivity.application.rest.item.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeRequest;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeResponse;
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
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/v1/item")
@Api(tags = {"Item APIs to ERP"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class ErpItemApiController {
    private final ItemApplicationService service;

    @PutMapping("/volume")
    @ApiOperation(value = "update item volume (상품 체적정보)", notes = "update item volume")
    public GenericResponse<ErpItemVolumeResponse> updateItemVolume(
            @Validated(ValidationSequence.class)
            @RequestBody
            ErpItemVolumeRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/erp/v1/item/volume, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                ErpItemVolumeResponse.getUpdateVolumeResponseMessageBy(servletRequest),
                service.updateVolume(request),
                OK
        );
    }

}
