package ai.fassto.connectivity.domain.workorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.WorkOrderResponseId;
import lombok.Getter;

@Getter
public class WorkOrderResponse extends BaseEntity<WorkOrderResponseId> {
    private Integer itemSeq;

    private WorkOrderResponse(Builder builder){
        setId(builder.id);
        itemSeq = builder.itemSeq;
    }

    public static final class Builder{
        private WorkOrderResponseId id;
        private Integer itemSeq;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(WorkOrderResponseId val){
            id = val;
            return this;
        }

        public Builder itemSeq(Integer val){
            itemSeq = val;
            return this;
        }

        public WorkOrderResponse build(){
            return new WorkOrderResponse(this);
        }
    }
}
