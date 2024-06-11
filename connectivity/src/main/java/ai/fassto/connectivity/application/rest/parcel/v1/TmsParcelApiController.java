package ai.fassto.connectivity.application.rest.parcel.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.TmsParcelApplicationService;
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
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carrier/v1/parcel")
@Api(tags = {"Parcel APIs to Carrier"})
public class TmsParcelApiController {
    private final TmsParcelApplicationService service;


    @PostMapping("/tms-pre-call")
    public GenericResponse<TmsPreCallParcelResponse> callTmsPre(
            @Validated(ValidationSequence.class)
            @RequestBody
            TmsPreCallParcelRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/carrier/v1/parcel/tms-pre-call, params: {}", toJson(request));

        return new GenericResponse<>(
                TmsPreCallParcelResponse.getResponseMessageBy(servletRequest),
                service.createTmsPreCall(request),
                OK
        );
    }

    @PostMapping("/tms-final-call")
    public GenericResponse<TmsFinalCallParcelResponse> callTmsFinal(
            @Validated(ValidationSequence.class)
            @RequestBody
            TmsFinalCallParcelRequest request,
            HttpServletRequest servletRequest
    ) {
        log.info("POST /api/carrier/v1/parcel/tms-final-call, params: {}", toJson(request));

        return new GenericResponse<>(
                TmsFinalCallParcelResponse.getResponseMessageBy(servletRequest),
                service.createTmsFinalCall(request),
                OK
        );
    }
}
