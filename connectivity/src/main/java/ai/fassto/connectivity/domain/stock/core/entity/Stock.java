package ai.fassto.connectivity.domain.stock.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.stock.core.valueobject.StockId;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Stock extends AggregateRoot<StockId> {
    private Integer changeQuantity;
    private LocalDate expiryDate;
    private LocalDate makeDate;
    private String adjustmentReasonCode;
    private String type;

    private Stock(Builder builder) {
        setId(builder.id);
        changeQuantity = builder.changeQuantity;
        expiryDate = builder.expiryDate;
        makeDate = builder.makeDate;
        adjustmentReasonCode = builder.adjustmentReasonCode;
        type = builder.type;
    }


    public static final class Builder {
        private StockId id;
        private Integer changeQuantity;
        private LocalDate expiryDate;
        private LocalDate makeDate;
        private String adjustmentReasonCode;
        private String type;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(StockId val) {
            id = val;
            return this;
        }

        public Builder changeQuantity(Integer val) {
            changeQuantity = val;
            return this;
        }

        public Builder expiryDate(LocalDate val) {
            expiryDate = val;
            return this;
        }

        public Builder makeDate(LocalDate val) {
            makeDate = val;
            return this;
        }

        public Builder adjustmentReasonCode(String val) {
            adjustmentReasonCode = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }
    }
}
