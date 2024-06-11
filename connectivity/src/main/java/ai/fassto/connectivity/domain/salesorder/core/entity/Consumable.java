package ai.fassto.connectivity.domain.salesorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ConsumableId;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Consumable extends BaseEntity<ConsumableId>{
    private Integer sequence;
    private String code;
    private Integer qty;
    private String size;
    private String type;
    private String useYn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumable that = (Consumable) o;
        return code.equals(that.code) && size.equals(that.size) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, size, type);
    }

    @Override
    public String toString() {
        return String.format("code:%s, qty:%d, size:%s, type:%s", this.code, this.qty, this.size, this.type);
    }

    private Consumable(Builder builder) {
        setId(builder.id);
        sequence = builder.sequence;
        code = builder.code;
        qty = builder.qty;
        size = builder.size;
        type = builder.type;
        useYn = builder.useYn;
    }

    public static final class Builder {
        private ConsumableId id;
        private Integer sequence;
        private String code;
        private Integer qty;
        private String size;
        private String type;
        private String useYn;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ConsumableId val) {
            id = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder sequence(Integer val) {
            sequence = val;
            return this;
        }

        public Builder qty(Integer val) {
            qty = val;
            return this;
        }

        public Builder size(String val) {
            size = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder useYn(String val) {
            useYn = val;
            return this;
        }

        public Consumable build() {
            return new Consumable(this);
        }

    }

}
