package ai.fassto.connectivity.domain.salesorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import lombok.Getter;

@Getter
public final class Customer extends BaseEntity<CustomerId> {
    private final String name;

    private Customer(Builder builder) {
        setId(builder.id);
        name = builder.name;
    }

    public static final class Builder {
        private CustomerId id;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CustomerId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
