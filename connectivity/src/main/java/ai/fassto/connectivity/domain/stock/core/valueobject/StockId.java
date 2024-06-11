package ai.fassto.connectivity.domain.stock.core.valueobject;

import ai.fassto.connectivity.dataaccess.common.valueobject.InventoryCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class StockId {
    private final String whCd; // WH_CD        varchar(10)                          not null comment '창고코드',
    private final String luNo; // LU_NO        varchar(20)                          not null comment 'LU번호',
    private final String lotNo; // LOT_NO       varchar(20)                          not null comment 'LOT번호',
    private final String godCd; // GOD_CD       varchar(30)                          not null comment '상품코드',
    private final InventoryCondition condition; // CONDITION_CD varchar(5)                           not null comment '재고상태',

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockId stockId = (StockId) o;
        return whCd.equals(stockId.whCd) && luNo.equals(stockId.luNo) && lotNo.equals(stockId.lotNo) && godCd.equals(stockId.godCd) && condition.equals(stockId.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whCd, luNo, lotNo, godCd, condition);
    }
}
