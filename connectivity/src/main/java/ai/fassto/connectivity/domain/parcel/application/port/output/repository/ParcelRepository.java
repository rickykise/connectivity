package ai.fassto.connectivity.domain.parcel.application.port.output.repository;

import ai.fassto.connectivity.domain.parcel.core.entitiy.TmsPreCall;
import ai.fassto.connectivity.domain.parcel.core.valueobject.OutOrder;

import java.util.Optional;

public interface ParcelRepository {
    int updateOutOrdParcelCdInvoiceNo(TmsPreCall tmsPreCall);

    Optional<OutOrder> findOneOutOrdOptional(TmsPreCall tmsPreCall);
}
