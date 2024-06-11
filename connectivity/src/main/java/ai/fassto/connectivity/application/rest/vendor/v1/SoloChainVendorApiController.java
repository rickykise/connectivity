package ai.fassto.connectivity.application.rest.vendor.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorRequest;
import ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse;
import ai.fassto.connectivity.domain.vendor.application.port.input.service.VendorApplicationService;
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
import static ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse.getCreateResponseMessageBy;
import static ai.fassto.connectivity.domain.vendor.application.dto.SoloChainVendorResponse.getUpdateResponseMessageBy;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1/vendor")
@Api(tags = {"Vendor APIs to Solochain"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class SoloChainVendorApiController {
    private final VendorApplicationService service;

    @PostMapping
    @ApiOperation(value = "Create Vendor (공급사 생성)", notes = "Create Vendor")
    public GenericResponse<SoloChainVendorResponse> createVendor(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainVendorRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/vendor, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping
    @ApiOperation(value = "Update Vendor (공급사 수정)", notes = "Update Vendor")
    public GenericResponse<SoloChainVendorResponse> updateVendor(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainVendorRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/vendor, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }
}
