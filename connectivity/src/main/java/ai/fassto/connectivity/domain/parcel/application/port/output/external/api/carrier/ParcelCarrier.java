package ai.fassto.connectivity.domain.parcel.application.port.output.external.api.carrier;


import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;

public interface ParcelCarrier {
    TmsPreCallParcelResponse sendPreCall(TmsPreCallParcelRequest request);

    TmsFinalCallParcelResponse sendFinalCall(TmsFinalCallParcelRequest request);
}
