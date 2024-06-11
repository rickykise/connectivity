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
public class OutOrdSetEntity {

    private String ordDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String shopCd;
    private String godCd;
    private String distTermDt;
    private String lotNo;
    private Integer ordQty;
    private Integer setQty;
    private String ordNo;
    private Integer ordSeq;
    private String godCancelYn;
    private String ordCancelYn;
    private String pickGodGear;
    private String sortGodGear;

}
