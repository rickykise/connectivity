package ai.fassto.connectivity.dataaccess.parcel.mapper;

import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.dataaccess.parcel.dto.OutOrdDTO;
import ai.fassto.connectivity.dataaccess.parcel.entity.OutOrdEntity;

import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.id.ShopId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.parcel.core.entitiy.TmsPreCall;
import ai.fassto.connectivity.domain.parcel.core.valueobject.OutOrder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyyMMdd;

@Component
public class ParcelDataAccessMapper {

    public OutOrdEntity tmsPreCallToOutOrdEntity(TmsPreCall tmsPreCall) {
        return OutOrdEntity.builder()
                .slipNo(tmsPreCall.getOutOrdSlipNo())
                .whCd(tmsPreCall.getWhCd())
                .parcelCd(tmsPreCall.getParcelCd())
                .invoiceNo(tmsPreCall.getInvoiceNo())
                .build();
    }

    public OutOrdDTO tmsPreCallToOutOrdDTO(TmsPreCall tmsPreCall) {
        return OutOrdDTO.builder()
                .slipNo(tmsPreCall.getOutOrdSlipNo())
                .whCd(tmsPreCall.getWhCd())
                .cstCd(tmsPreCall.getCstCd())
                .build();
    }

    public Optional<OutOrder> outOrdEntityToOutOrder(OutOrdEntity outOrdEntityOrNull) {
        if (outOrdEntityOrNull == null) {
            return Optional.empty();
        }

        return Optional.of(OutOrder.builder().build());
    }

}
