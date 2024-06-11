package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPackEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OutPackBulkInsertDTO {
    private String packDt;
    private String whCd;
    private Integer packSeq;
    private String outOrdSlipNo;
    private String cstCd;
    private String shopCd;
    private String ordNo;
    private Integer ordSeq;
    private String boxDiv;
    private String boxNo;
    private String boxTp;
    private Integer bscFare;
    private Integer airFare;
    private Integer dealFare;
    private Integer shipFare;
    private String parcelCd;
    private String invoiceNo;
    private String workTp;
    private Integer boxQuantity;
    private Integer disposablePalette;
    private Integer fssPalette;
    private Integer kppPalette;
    private Integer oneWoodPalette;
    private String gearLocNumb;
    private String palletYn;
    private String addSubMateYn;
    private List<OutPackEntity> outPackEntityList;

    @Builder
    public OutPackBulkInsertDTO(String packDt, String whCd, Integer packSeq, String outOrdSlipNo, String cstCd, String shopCd, String ordNo, Integer ordSeq, String boxDiv, String boxNo, String boxTp, Integer bscFare, Integer airFare, Integer dealFare, Integer shipFare, String parcelCd, String invoiceNo, String workTp, Integer boxQuantity, Integer disposablePalette, Integer fssPalette, Integer kppPalette, Integer oneWoodPalette, String gearLocNumb, String palletYn, String addSubMateYn, List<OutPackEntity> outPackEntityList) {
        this.packDt = packDt;
        this.whCd = whCd;
        this.packSeq = packSeq;
        this.outOrdSlipNo = outOrdSlipNo;
        this.cstCd = cstCd;
        this.shopCd = shopCd;
        this.ordNo = ordNo;
        this.ordSeq = ordSeq;
        this.boxDiv = boxDiv;
        this.boxNo = boxNo;
        this.boxTp = boxTp;
        this.bscFare = bscFare;
        this.airFare = airFare;
        this.dealFare = dealFare;
        this.shipFare = shipFare;
        this.parcelCd = parcelCd;
        this.invoiceNo = invoiceNo;
        this.workTp = workTp;
        this.boxQuantity = boxQuantity;
        this.disposablePalette = disposablePalette;
        this.fssPalette = fssPalette;
        this.kppPalette = kppPalette;
        this.oneWoodPalette = oneWoodPalette;
        this.gearLocNumb = gearLocNumb;
        this.palletYn = palletYn;
        this.addSubMateYn = addSubMateYn;
        this.outPackEntityList = outPackEntityList;
    }


}
