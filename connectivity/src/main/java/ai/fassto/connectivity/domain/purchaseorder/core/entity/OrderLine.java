package ai.fassto.connectivity.domain.purchaseorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.OrderLineId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.QuantityInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.VehicleInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderLine extends BaseEntity<OrderLineId> {
    private Customer customer;
    private ItemSimple itemSimple;
    private LocalDate orderDate;
    private WarehouseId warehouseId; //  whCd;
    private QuantityInfo quantityInfo;
    private VehicleInfo vehicleInfo;
    private String releaseType; // 출고 가능 타입 0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고
    private String releaseTypeName; // 출고 가능 타입 0: 당일 출고, 1: 익일 출고, 2: D+2 이상 출고
    private String vendorCode;


    private OrderLine(Builder builder) {
        setId(builder.id);
        customer = builder.customer;
        itemSimple = builder.itemSimple;
        orderDate = builder.orderDate;
        warehouseId = builder.warehouseId;
        quantityInfo = builder.quantityInfo;
        vehicleInfo = builder.vehicleInfo;
        releaseType = builder.releaseType;
        releaseTypeName = builder.releaseTypeName;
        vendorCode = builder.vendorCode;
    }


    public static final class Builder {
        private OrderLineId id;
        private Customer customer;
        private ItemSimple itemSimple;
        private LocalDate orderDate;
        private WarehouseId warehouseId;
        private QuantityInfo quantityInfo;
        private VehicleInfo vehicleInfo;
        private String releaseType;
        private String releaseTypeName;
        private String vendorCode;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderLineId val) {
            id = val;
            return this;
        }

        public Builder customer(Customer val) {
            customer = val;
            return this;
        }

        public Builder itemSimple(ItemSimple val) {
            itemSimple = val;
            return this;
        }

        public Builder orderDate(LocalDate val) {
            orderDate = val;
            return this;
        }

        public Builder warehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder quantityInfo(QuantityInfo val) {
            quantityInfo = val;
            return this;
        }

        public Builder vehicleInfo(VehicleInfo val) {
            vehicleInfo = val;
            return this;
        }

        public Builder releaseType(String val) {
            releaseType = val;
            return this;
        }

        public Builder releaseTypeName(String val) {
            releaseTypeName = val;
            return this;
        }

        public Builder vendorCode(String val) {
            vendorCode = val;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }
}
