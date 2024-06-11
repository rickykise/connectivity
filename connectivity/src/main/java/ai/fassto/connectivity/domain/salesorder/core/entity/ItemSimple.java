package ai.fassto.connectivity.domain.salesorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import lombok.Getter;

/**
 * Item으로 만들려고 했으나 이미 Item을 사용하고 있어서 ItemSimple로 만들었음
 * 상품코드 = 고객사 코드 + 고객사의 코드 (GOD_CD = CST_CD + CST_GOD_CD)
 * */
@Getter
public final class ItemSimple extends BaseEntity<ItemId> {
    private final String name;
    private final String itemCodeOfCustomer;
    private final String type;


    private ItemSimple(Builder builder) {
        setId(builder.id);
        name = builder.name;
        itemCodeOfCustomer = builder.itemCodeOfCustomer;
        type = builder.type;
    }


    public static final class Builder {
        private ItemId id;
        private String name;
        private String itemCodeOfCustomer;
        private String type;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ItemId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder itemCodeOfCustomer(String val) {
            itemCodeOfCustomer = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public ItemSimple build() {
            return new ItemSimple(this);
        }
    }
}
