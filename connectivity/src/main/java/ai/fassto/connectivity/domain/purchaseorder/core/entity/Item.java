package ai.fassto.connectivity.domain.purchaseorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.OrderLineId;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Item extends BaseEntity<ItemId> {
    private OrderLineId orderLineId;
    private String receiptNo;
    private String lotNo;

    private String condition;

    private String memo;
    private String imagePath;

    private LocalDate makeDate;
    private LocalDate expiryDate;


    /**
     * Quantities
     */
    private String uoi;
    private Integer quantity;
    private Integer baseQuantity;
    private Integer barcodeQuantity;

    private Item(Builder builder) {
        setId(builder.id);
        orderLineId = builder.orderLineId;
        receiptNo = builder.receiptNo;
        lotNo = builder.lotNo;
        condition = builder.condition;
        memo = builder.memo;
        imagePath = builder.imagePath;
        makeDate = builder.makeDate;
        expiryDate = builder.expiryDate;
        uoi = builder.uoi;
        quantity = builder.quantity;
        baseQuantity = builder.baseQuantity;
        barcodeQuantity = builder.barcodeQuantity;
    }


    public static final class Builder {
        private ItemId id;
        private OrderLineId orderLineId;
        private String receiptNo;
        private String lotNo;
        private String condition;
        private String memo;
        private String imagePath;
        private LocalDate makeDate;
        private LocalDate expiryDate;
        private String uoi;
        private Integer quantity;
        private Integer baseQuantity;
        private Integer barcodeQuantity;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ItemId val) {
            id = val;
            return this;
        }

        public Builder orderLineId(OrderLineId val) {
            orderLineId = val;
            return this;
        }

        public Builder receiptNo(String val) {
            receiptNo = val;
            return this;
        }

        public Builder lotNo(String val) {
            lotNo = val;
            return this;
        }


        public Builder condition(String val) {
            condition = val;
            return this;
        }

        public Builder memo(String val) {
            memo = val;
            return this;
        }

        public Builder imagePath(String val) {
            imagePath = val;
            return this;
        }

        public Builder makeDate(LocalDate val) {
            makeDate = val;
            return this;
        }

        public Builder expiryDate(LocalDate val) {
            expiryDate = val;
            return this;
        }

        public Builder uoi(String val) {
            uoi = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder baseQuantity(Integer val) {
            baseQuantity = val;
            return this;
        }

        public Builder barcodeQuantity(Integer val) {
            barcodeQuantity = val;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
