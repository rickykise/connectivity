package ai.fassto.connectivity.domain.workorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.common.valueobject.id.WorkOrderId;
import ai.fassto.connectivity.domain.workorder.enums.WorkDirection;
import ai.fassto.connectivity.domain.workorder.enums.WorkOrderStatus;
import ai.fassto.connectivity.domain.workorder.enums.WorkType;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
final public class WorkOrder extends AggregateRoot<WorkOrderId> {

    private LocalDateTime workDate;
    private WorkOrderStatus workOrderStatus;
    private String memo;
    private WorkType type;
    private String workerTime;
    private String workerCnt;
    private List<OrderLine> orderLineList;
    private List<Component_> componentList;
    private ActionType actionType;
    private WarehouseId warehouseId;
    private CustomerId customerId;
    private LinkedMultiValueMap<WorkDirection, OrderLine> orderLineMultiValueMap;
    private LocalDate requiredDate;

    public List<OrderLine> getToOrderLineList() {
        return orderLineMultiValueMap.get(WorkDirection.TO);
    }

    public List<OrderLine> getFromOrderLineList() {
        return orderLineMultiValueMap.get(WorkDirection.FROM);
    }

    private WorkOrder(Builder builder) {
        setId(builder.id);
        workDate = builder.workDate;
        workOrderStatus = builder.workOrderStatus;
        memo = builder.memo;
        type = builder.type;
        workerTime = builder.workerTime;
        workerCnt = builder.workerCnt;
        orderLineList = builder.orderLineList;
        componentList = builder.componentList;
        actionType = builder.actionType;
        warehouseId = builder.warehouseId;
        customerId = builder.customerId;
        orderLineMultiValueMap = builder.orderLineMultiValueMap;
        requiredDate = builder.requiredDate;
    }


    public static final class Builder {
        private WorkOrderId id;
        private LocalDateTime workDate;
        private WorkOrderStatus workOrderStatus;
        private String memo;
        private WorkType type;
        private String workerTime;
        private String workerCnt;
        private List<OrderLine> orderLineList;
        private List<Component_> componentList;
        private ActionType actionType;
        private WarehouseId warehouseId;
        private CustomerId customerId;
        private LinkedMultiValueMap<WorkDirection, OrderLine> orderLineMultiValueMap;
        private LocalDate requiredDate;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(WorkOrderId val) {
            id = val;
            return this;
        }

        public Builder workDate(LocalDateTime val) {
            workDate = val;
            return this;
        }

        public Builder workStatus(WorkOrderStatus val) {
            workOrderStatus = val;
            return this;
        }

        public Builder memo(String val) {
            memo = val;
            return this;
        }

        public Builder type(WorkType val) {
            type = val;
            return this;
        }

        public Builder workerTime(String val) {
            workerTime = val;
            return this;
        }

        public Builder workerCnt(String val) {
            workerCnt = val;
            return this;
        }

        public Builder orderLineList(List<OrderLine> val) {
            orderLineList = val;
            return this;
        }

        public Builder componentList(List<Component_> val) {
            componentList = val;
            return this;
        }

        public Builder actionType(ActionType val) {
            actionType = val;
            return this;
        }

        public Builder warehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder orderLineMultiValueMap(LinkedMultiValueMap<WorkDirection, OrderLine> val) {
            orderLineMultiValueMap = val;
            return this;
        }

        public Builder requiredDate(LocalDate val) {
            requiredDate = val;
            return this;
        }

        public WorkOrder build() {
            return new WorkOrder(this);
        }
    }
}
