package ai.fassto.connectivity.domain.salesorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ShopId;
import lombok.Getter;

@Getter
public final class Shop extends BaseEntity<ShopId> {
    private String name;

    private Shop(Builder builder) {
        setId(builder.id);
        name = builder.name;
    }


    public static final class Builder {
        private ShopId id;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ShopId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Shop build() {
            return new Shop(this);
        }
    }
}
