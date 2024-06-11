package ai.fassto.connectivity.dataaccess.stock.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class WarehouseInventoryHistoryEntity {
    private Long seq; //                     bigint unsigned auto_increment comment 'seq'    primary key,
    private String whCd; //                   varchar(10)                          not null comment '창고코드',
    private String luNo; //                   varchar(20)                          not null comment 'LU번호',
    private String lotNo; //                  varchar(20)                          not null comment 'LOT번호',
    private String godCd; //                  varchar(30)                          not null comment '상품코드',
    private String conditionCd; //            varchar(5)                           not null comment '재고상태',
    private String distTermDt; //            varchar(8)                           null comment '유통기한',
    private String makeDt; //                 varchar(8)                           null comment '제조일자',
    private Integer stockQty; //               int(10)  default 0                   not null comment '재고수량',
    private String adjustmentReasonCode; //  varchar(100)                         null comment '재고변경사유코드',
    private String type; //                    varchar(50)                          null comment '타입',
    private LocalDateTime regTime; //                datetime default current_timestamp() not null comment '등록시간'
}
