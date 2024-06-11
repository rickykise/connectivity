package ai.fassto.connectivity.domain.parcel.application;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.TmsParcelApplicationService;
import ai.fassto.connectivity.domain.parcel.application.port.output.external.api.carrier.ParcelCarrier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TmsParcelApplicationServiceImpl implements TmsParcelApplicationService {
    private final ParcelCarrier parcelCarrier;

    @Override
    public TmsPreCallParcelResponse createTmsPreCall(TmsPreCallParcelRequest request) {
        return parcelCarrier.sendPreCall(request);
    }

    @Override
    public TmsFinalCallParcelResponse createTmsFinalCall(TmsFinalCallParcelRequest request) {
        return parcelCarrier.sendFinalCall(request);
    }
}
