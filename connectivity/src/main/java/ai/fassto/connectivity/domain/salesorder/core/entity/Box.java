package ai.fassto.connectivity.domain.salesorder.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Box {
    private Integer boxSeq;
    private String boxId;
    private String boxType;
    private String category;
    private String courierCode;
    private String invoiceNo;
    private Integer basicFare;
    private Integer dealFare;
    private Integer airFare;
    private Integer shipFare;
    private String realPackingVideo;
}
