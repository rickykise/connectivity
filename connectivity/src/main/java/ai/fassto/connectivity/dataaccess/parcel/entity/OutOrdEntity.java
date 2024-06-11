package ai.fassto.connectivity.dataaccess.parcel.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OutOrdEntity {
    private String ordDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String shopCd;
    private String godCd;
    private String distTermDt;
    private String bundleYn;
    private Integer ordQty;
    private Integer addGodOrdQty;
    private String addType;
    private Integer addSubMateQty;
    private String outDiv;
    private Byte outTp;
    private String ordNo;
    private Integer ordSeq;
    private String cstDiv;
    private String cstDtlSeq;
    private String cstClaNo;
    private String godOrdNo;
    private String outWay;
    private String abroShipYn;
    private String wrkStat;
    private String pickDiv;
    private String ordDiv;
    private String frontDoorPWd;
    private String oneDayDeliveryCd;
    private String parcelCd;
    private String invoiceNo;
    private String cstCancelDiv;
    private String cancelStep;
    private String cancelId;
    private String cancelTime;
    private String updId;
    private String emgrYn;
    private String remark;
    private String partOrderYn;
    private String shopPayTime;
    private String shipConfirmTime;
    private Integer supplyPrice;
    private Integer price;
}
