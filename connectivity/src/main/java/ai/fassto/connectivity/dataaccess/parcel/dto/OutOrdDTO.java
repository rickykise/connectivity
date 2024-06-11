package ai.fassto.connectivity.dataaccess.parcel.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OutOrdDTO {
    private String whCd;
    private String slipNo;
    private String cstCd;
}
