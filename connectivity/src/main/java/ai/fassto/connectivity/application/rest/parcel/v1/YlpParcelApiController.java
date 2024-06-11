package ai.fassto.connectivity.application.rest.parcel.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.YlpParcelApplicationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp.VehicleAllocationYlpResponse.getVehicleAllocationYlpResponseMessageBy;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carrier/v1/parcel")
@Api(tags = {"Parcel APIs to Carrier"})
public class YlpParcelApiController {

    private final YlpParcelApplicationService service;

    @PostMapping("/vehicle-allocation-ylp")
    public GenericResponse<VehicleAllocationYlpResponse> allocateVehicleForYLP(
            @Validated(ValidationSequence.class)
            @RequestBody
            VehicleAllocationYlpRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/carrier/v1/parcel/vehicle-allocation-ylp, params: {}", toJson(request));

        return new GenericResponse<>(
                getVehicleAllocationYlpResponseMessageBy(servletRequest),
                service.createVehicleAllocate(request),
                OK
        );
    }


}
