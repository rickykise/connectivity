package ai.fassto.connectivity.domain.purchaseorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import lombok.Getter;

/**
 * Item으로 만들려고 했으나 이미 Item을 사용하고 있어서 ItemSimple로 만들었음
 * 상품코드 = 고객사 코드 + 고객사의 코드 (GOD_CD = CST_CD + CST_GOD_CD)
 * */
@Getter
public class ItemSimple extends BaseEntity<ItemId> {
    private final String name;
    private final String itemCodeOfCustomer;
    private final String barcode;
    private final Boolean firstPurchaseOrder; // 상품 최초입고 여부
    private final Boolean enabled;

    private ItemSimple(Builder builder) {
        setId(builder.id);
        name = builder.name;
        itemCodeOfCustomer = builder.itemCodeOfCustomer;
        barcode = builder.barcode;
        firstPurchaseOrder = builder.firstPurchaseOrder;
        enabled = builder.enabled;
    }


    public static final class Builder {
        private ItemId id;
        private String name;
        private String itemCodeOfCustomer;
        private String barcode;
        private Boolean firstPurchaseOrder;
        private Boolean enabled;

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

        public Builder barcode(String val) {
            barcode = val;
            return this;
        }

        public Builder firstPurchaseOrder(Boolean val) {
            firstPurchaseOrder = val;
            return this;
        }

        public Builder enabled(Boolean val) {
            enabled = val;
            return this;
        }

        public ItemSimple build() {
            return new ItemSimple(this);
        }
    }
}
