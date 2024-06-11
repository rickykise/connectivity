package ai.fassto.connectivity.domain.salesorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.OrderLineId;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.QuantityInfo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class OrderLine extends BaseEntity<OrderLineId> {
    private ItemSimple itemSimple;
    private QuantityInfo quantityInfo;
    private String outWay;
    private String lotNo;
    private LocalDate expiryDate;
    private String condition;

    private OrderLine(Builder builder) {
        setId(builder.id);
        itemSimple = builder.itemSimple;
        quantityInfo = builder.quantityInfo;
        outWay = builder.outWay;
        lotNo = builder.lotNo;
        expiryDate = builder.expiryDate;
        condition = builder.condition;
    }


    public static final class Builder {
        private OrderLineId id;
        private ItemSimple itemSimple;
        private QuantityInfo quantityInfo;
        private String outWay;
        private String lotNo;
        private LocalDate expiryDate;
        private String condition;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderLineId val) {
            id = val;
            return this;
        }

        public Builder itemSimple(ItemSimple val) {
            itemSimple = val;
            return this;
        }

        public Builder quantityInfo(QuantityInfo val) {
            quantityInfo = val;
            return this;
        }

        public Builder outWay(String val) {
            outWay = val;
            return this;
        }

        public Builder lotNo(String val) {
            lotNo = val;
            return this;
        }

        public Builder expiryDate(LocalDate val) {
            expiryDate = val;
            return this;
        }

        public Builder condition(String val) {
            condition = val;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }
}
