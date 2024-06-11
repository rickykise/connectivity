package ai.fassto.connectivity.dataaccess.parcel.repository.mybatis;

import ai.fassto.connectivity.dataaccess.parcel.dto.OutOrdDTO;
import ai.fassto.connectivity.dataaccess.parcel.entity.OutOrdEntity;

public interface ParcelMapper {
    int updateOutOrdParcelCdInvoiceNo(OutOrdEntity outOrdEntity);

    OutOrdEntity findOneOutOrd(OutOrdDTO outOrdDTO);
}
