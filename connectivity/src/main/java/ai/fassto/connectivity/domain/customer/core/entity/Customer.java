package ai.fassto.connectivity.domain.customer.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.common.valueobject.BusinessRegistration;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.EmployeeInCharge;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.EStatus;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class Customer extends AggregateRoot<CustomerId> {
    private String name; // cstNm;
    private EStatus status;

    private BusinessRegistration businessRegistration;
    private HashMap<Integer, EmployeeInCharge> employeeMap;
    private ActionType actionType;
    private WarehouseId warehouseId;

    private Customer(Builder builder) {
        setId(builder.customerId);
        name = builder.name;
        status = builder.status;
        businessRegistration = builder.businessRegistration;
        employeeMap = builder.employeeMap;
        actionType = builder.actionType;
        warehouseId = builder.warehouseId;
    }


    public static final class Builder {
        private CustomerId customerId;
        private String name;
        private EStatus status;
        private BusinessRegistration businessRegistration;
        private HashMap<Integer, EmployeeInCharge> employeeMap;
        private ActionType actionType;
        private WarehouseId warehouseId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder status(EStatus val) {
            status = val;
            return this;
        }

        public Builder businessRegistration(BusinessRegistration val) {
            businessRegistration = val;
            return this;
        }

        public Builder employeeMap(HashMap<Integer, EmployeeInCharge> val) {
            employeeMap = val;
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

        public Customer build() {
            return new Customer(this);
        }
    }
}
