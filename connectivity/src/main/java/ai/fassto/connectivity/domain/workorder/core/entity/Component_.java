package ai.fassto.connectivity.domain.workorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.OrderLineId;
import ai.fassto.connectivity.domain.workorder.core.valueobject.QuantityInfo_;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Component_ extends BaseEntity<OrderLineId> {

    private ItemId itemId;
    private QuantityInfo_ quantityInfo_;
    private Component_(Builder builder) {
        setId(builder.id);
        itemId = builder.itemId;
        quantityInfo_ = builder.quantityInfo_;
    }

    public static final class Builder {
        private OrderLineId id;
        private ItemId itemId;
        private QuantityInfo_ quantityInfo_;

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

        public Builder quantityInfo_(QuantityInfo_ val) {
            quantityInfo_ = val;
            return this;
        }

        public Component_ build() {
            return new Component_(this);
        }

    }

}
