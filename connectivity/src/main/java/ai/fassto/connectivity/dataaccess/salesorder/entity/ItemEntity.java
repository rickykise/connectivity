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
public class ItemEntity {

    private Integer ordSeq;
    private String godCd;
    private String distTermDt;
    private String makeDt;
    private String lotNo;
    private String locNo;
    private String luNo;
    private Integer ordQty;
    private Integer setQty;
    private Integer pickQty;
    private Integer packQty;
    private String unitCode;
    private Integer unitQty;
    private String boxDiv;
    private String boxNo;
    private String boxTp;
    private Integer bscFare;
    private Integer airFare;
    private Integer dealFare;
    private Integer shipFare;
    private String parcelCd;
    private String invoiceNo;

}
