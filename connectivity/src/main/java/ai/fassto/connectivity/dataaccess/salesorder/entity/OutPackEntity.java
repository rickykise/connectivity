package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutPackEntity {
    String packDt;
    String whCd;
    Integer packSeq;
    String outOrdSlipNo;
    String cstCd;
    String shopCd;
    String godCd;
    Integer packQty;
    String ordNo;
    Integer ordSeq;
    String boxDiv;
    String boxTp;
    String boxNo;
    Integer bscFare;
    Integer airFare;
    Integer dealFare;
    Integer shipFare;
    String parcelCd;
    String invoiceNo;
    String workTp;
    Integer boxQuantity;
    Integer disposablePalette;
    Integer fssPalette;
    String rtnYn;
    String regId;
    String updId;
    String dlvMisYn;
    Integer kppPalette;
    Integer oneWoodPalette;
    Integer printCnt;
    String gearLocNumb;
    String migTime;
    String palletYn;
    String addSubMateYn;
    String realPackingVideo;
}
