package ai.fassto.connectivity.domain.salesorder.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class Item {
    private String receiptNo;
    private Integer sequence;
    private String code;
    private Integer qty;
    private String status;
    private String memo;
    private String loc;
    private String lu;
    private String lot;
    private LocalDate expiryDate;
    private Integer boxSeq;
    private String godDiv;
}
