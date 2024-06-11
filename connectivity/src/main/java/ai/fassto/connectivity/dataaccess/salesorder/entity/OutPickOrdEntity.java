package ai.fassto.connectivity.dataaccess.salesorder.entity;

import java.time.LocalDateTime;
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
public class OutPickOrdEntity {

    private String pickDt;
    private String whCd;
    private Integer pickSeq;
    private String outOrdSlipNo;
    private String cstCd;
    private String shopCd;
    private String locNo;
    private String luNo;
    private String lotNo;
    private String godCd;
    private Integer setQty;
    private Integer pickQty;
    private Integer packQty;
    private String ordNo;
    private Integer ordSeq;
    private String wrkStat;
    private String pickGear;
    private String pickId;
    private LocalDateTime pickTime;
    private String packId;
    private LocalDateTime packTime;
    private String packGodCancelYn;

}
