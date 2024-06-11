package ai.fassto.connectivity.dataaccess.purchaseorder.entity;

import java.time.LocalDateTime;
import java.util.List;
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
public class InOrdEntity {
    private String ordDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String supCd;
    private String godCd;
    private String makeDt;
    private String distTermDt;
    private Integer ordQty;
    private String inDiv;
    private Integer inPr;
    private String inWay;
    private String parcelComp;
    private String parcelInvoiceNo;
    private String rtnGathYn;
    private String wrkStat;
    private String ordEndDiv;
    private String regId;
    private LocalDateTime regTime;
    private String updId;
    private LocalDateTime updTime;
    private String regUpdId;
    private LocalDateTime regUpdTime;
    private String emgrYn;
    private String remark;
    private Integer ordSeq;
    private String preArv;
    private String releaseDt;
    private String releaseGbn;
    private String cenArvGbn;
    private String cenArvDt;
    private String cenArvTime;
}
