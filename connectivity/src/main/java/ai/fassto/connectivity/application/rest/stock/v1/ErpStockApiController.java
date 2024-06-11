package ai.fassto.connectivity.application.rest.stock.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockRequest;
import ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockResponse;
import ai.fassto.connectivity.domain.stock.application.port.input.service.StockApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.stock.application.dto.erp.update.UpdateErpStockResponse.getUpdateStockResponseMessageBy;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/v1/stock")
@Api(tags = {"Stock APIs to ERP"})
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class ErpStockApiController {
    private final StockApplicationService service;

    @PutMapping
    public GenericResponse<UpdateErpStockResponse> updateStock(
            @Validated(ValidationSequence.class)
            @RequestBody
            UpdateErpStockRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("PUT /api/erp/v1/stock, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getUpdateStockResponseMessageBy(servletRequest),
                service.update(request),
                OK
        );
    }
}
