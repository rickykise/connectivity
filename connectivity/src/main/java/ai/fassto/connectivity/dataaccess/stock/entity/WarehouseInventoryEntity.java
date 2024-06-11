package ai.fassto.connectivity.dataaccess.stock.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class WarehouseInventoryEntity {
    private String whCd; // WH_CD        varchar(10)                          not null comment '창고코드',
    private String luNo; // LU_NO        varchar(20)                          not null comment 'LU번호',
    private String lotNo; // LOT_NO       varchar(20)                          not null comment 'LOT번호',
    private String godCd; // GOD_CD       varchar(30)                          not null comment '상품코드',
    private String conditionCd; // CONDITION_CD varchar(5)                           not null comment '재고상태',
    private String makeDt; // MAKE_DT      varchar(8)                           null comment '제조일자',
    private String distTermDt; // DIST_TERM_DT varchar(8)                           null comment '유통기한일자',
    private Integer stockQty; // STOCK_QTY    int(10)  default 0                   not null comment '재고수량',
    private String regId; // REG_ID       varchar(36)                          null comment '등록자',
    private LocalDateTime regTime; // REG_TIME     datetime default current_timestamp() not null comment '등록시간',
    private String updId; // UPD_ID       varchar(36)                          null comment '수정자',
    private LocalDateTime updTime; // UPD_TIME     datetime default current_timestamp() not null comment '수정시간',
}
