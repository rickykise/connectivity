package ai.fassto.connectivity.domain.workorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import lombok.Getter;

@Getter
public class ItemSimple extends BaseEntity<ItemId> {

    private ItemSimple(Builder builder){
        setId(builder.id);
    }

    public static final class Builder{
        private ItemId id;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ItemId val){
            id = val;
            return this;
        }

        public ItemSimple build(){
            return new ItemSimple(this);
        }
    }

}
