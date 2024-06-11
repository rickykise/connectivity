package ai.fassto.connectivity.application.rest.customer.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerRequest;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerResponse;
import ai.fassto.connectivity.domain.customer.application.port.input.service.CustomerApplicationService;
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
import static ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerResponse.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1/customer")
@Api(tags = {"Customer APIs to SOLOCHAIN"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class SoloChainCustomerApiController {
    private final CustomerApplicationService service;

    @PostMapping
    @ApiOperation(value = "Create Customer (고객사 생성)", notes = "Create Customer")
    public GenericResponse<SoloChainCustomerResponse> createCustomer(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainCustomerRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/customer, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreateResponseMessageBy(servletRequest),
                service.create(request),
                CREATED
        );
    }

    @PutMapping
    @ApiOperation(value = "Update Customer (고객사 수정)", notes = "Update Customer")
    public GenericResponse<SoloChainCustomerResponse> updateCustomer(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainCustomerRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/solochain/v1/customer, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }
}
