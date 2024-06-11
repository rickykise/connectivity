package ai.fassto.connectivity.dataaccess.stock.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class WarehouseInventoryPartnerApiEntity {
    private String godCd; //                  varchar(30)                          not null comment '상품코드',
    private Integer stockQty; //               int(10)  default 0                   not null comment '재고수량',
    private String conditionCd; //            varchar(5)                           not null comment '재고상태',
    private String adjustmentReasonCode; //  varchar(100)                         null comment '재고변경사유코드',
}
