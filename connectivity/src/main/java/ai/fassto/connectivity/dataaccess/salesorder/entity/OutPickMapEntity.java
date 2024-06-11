package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OutPickMapEntity {

    private String mapSlipNo;
    private String outOrdSlipNo;
    private String whCd;
    private String cstCd;
    private String pickDt;
    private final String pickOrdType = "O";
    private String allocateYn;
    private String regId;
    private String updId;

}
