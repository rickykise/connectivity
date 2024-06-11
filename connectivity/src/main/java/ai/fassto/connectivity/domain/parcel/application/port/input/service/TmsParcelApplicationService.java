package ai.fassto.connectivity.domain.parcel.application.port.input.service;


import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsFinalCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;

public interface TmsParcelApplicationService {

    TmsPreCallParcelResponse createTmsPreCall(TmsPreCallParcelRequest request);

    TmsFinalCallParcelResponse createTmsFinalCall(TmsFinalCallParcelRequest request);

}
