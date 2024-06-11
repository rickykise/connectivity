package ai.fassto.connectivity.domain.salesorder.core.valueobject;

import ai.fassto.connectivity.domain.common.valueobject.id.SalesOrderId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import lombok.Builder;

import java.util.Objects;

@Builder
public record OutInventoryAssign(
        SalesOrderId salesOrderId,
        Integer itemSeq,
        WarehouseId warehouseId,
        String lotNo,
        String godCd,
        Integer assignQty
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutInventoryAssign that = (OutInventoryAssign) o;
        return itemSeq.equals(that.itemSeq) && lotNo.equals(that.lotNo) && godCd.equals(that.godCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemSeq, lotNo, godCd);
    }
}
