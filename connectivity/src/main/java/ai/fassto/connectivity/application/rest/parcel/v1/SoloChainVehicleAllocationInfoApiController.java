package ai.fassto.connectivity.application.rest.parcel.v1;


import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.VehicleAllocationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.parcel.application.dto.solochain.SoloChainVehicleAllocationInfoResponse.getVehicleAllocationInfoResponseMessageBy;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solochain/v1")
@Api(tags = {"VehicleAllocationInfo APIs to Solochain"})
public class SoloChainVehicleAllocationInfoApiController {

    private final VehicleAllocationService service;


    @PostMapping("/vehicle-allocation-info")
    public GenericResponse<SoloChainVehicleAllocationInfoResponse> updateVehicleAllocationInfo(
            @Validated(ValidationSequence.class)
            @RequestBody
            SoloChainVehicleAllocationInfoRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/solochain/v1/vehicle-allocation-info, params: {}", toJson(request));

        return new GenericResponse<>(
                getVehicleAllocationInfoResponseMessageBy(servletRequest),
                service.updateVehicleAllocationInfo(request),
                OK
        );
    }


}
