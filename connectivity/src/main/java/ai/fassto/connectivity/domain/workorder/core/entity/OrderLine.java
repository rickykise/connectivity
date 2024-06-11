package ai.fassto.connectivity.domain.workorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.OrderLineId;
import ai.fassto.connectivity.domain.workorder.core.valueobject.QuantityInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
final public class OrderLine extends BaseEntity<OrderLineId> {

    private ItemId itemId;
    private QuantityInfo quantityInfo;
    private String lotNo;

    private OrderLine(Builder builder) {
        setId(builder.id);
        itemId = builder.itemId;
        quantityInfo = builder.quantityInfo;
        lotNo = builder.lotNo;
    }


    public static final class Builder {
        private OrderLineId id;
        private ItemId itemId;
        private QuantityInfo quantityInfo;
        private String lotNo;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderLineId val) {
            id = val;
            return this;
        }

        public Builder itemId(ItemId val) {
            itemId = val;
            return this;
        }

        public Builder quantityInfo(QuantityInfo val) {
            quantityInfo = val;
            return this;
        }

        public Builder lotNo(String val) {
            lotNo = val;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }
}
