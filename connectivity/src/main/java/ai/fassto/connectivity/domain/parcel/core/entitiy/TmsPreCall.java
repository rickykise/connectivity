package ai.fassto.connectivity.domain.parcel.core.entitiy;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TmsPreCall {
    private String outOrdSlipNo;
    private String whCd;
    private String cstCd;
    private String parcelCd;
    private String invoiceNo;
}
