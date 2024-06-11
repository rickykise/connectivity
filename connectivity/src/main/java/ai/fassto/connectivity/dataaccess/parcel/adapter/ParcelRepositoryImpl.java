package ai.fassto.connectivity.dataaccess.parcel.adapter;

import ai.fassto.connectivity.dataaccess.parcel.dto.OutOrdDTO;
import ai.fassto.connectivity.dataaccess.parcel.entity.OutOrdEntity;
import ai.fassto.connectivity.dataaccess.parcel.mapper.ParcelDataAccessMapper;
import ai.fassto.connectivity.dataaccess.parcel.repository.mybatis.ParcelMapper;
import ai.fassto.connectivity.domain.parcel.application.port.output.repository.ParcelRepository;
import ai.fassto.connectivity.domain.parcel.core.entitiy.TmsPreCall;
import ai.fassto.connectivity.domain.parcel.core.valueobject.OutOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ParcelRepositoryImpl implements ParcelRepository {
    private final ParcelMapper parcelMapper;
    private final ParcelDataAccessMapper parcelDataAccessMapper;

    @Override
    public int updateOutOrdParcelCdInvoiceNo(TmsPreCall tmsPreCall) {
        return parcelMapper.updateOutOrdParcelCdInvoiceNo(
                parcelDataAccessMapper.tmsPreCallToOutOrdEntity(tmsPreCall)
        );
    }

    @Override
    public Optional<OutOrder> findOneOutOrdOptional(TmsPreCall tmsPreCall) {
        return parcelDataAccessMapper.outOrdEntityToOutOrder(
                findOneOutOrdOrNull(
                        parcelDataAccessMapper.tmsPreCallToOutOrdDTO(tmsPreCall)
                )
        );

    }

    private OutOrdEntity findOneOutOrdOrNull(OutOrdDTO outOrdDTO){
        return parcelMapper.findOneOutOrd(outOrdDTO);
    }
}
