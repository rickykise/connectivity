package ai.fassto.connectivity.domain.vendor.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.common.valueobject.BusinessRegistration;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.EStatus;
import ai.fassto.connectivity.domain.common.valueobject.id.VendorId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import lombok.Getter;

@Getter
public class Vendor extends AggregateRoot<VendorId> {
    //    private String vendorId; // supCd; // ": "04233001",
    private BusinessRegistration businessRegistration;
    private EStatus status;
    private ActionType actionType;
    private WarehouseId warehouseId;

    private Vendor(Builder builder) {
        setId(builder.vendorId);
        businessRegistration = builder.businessRegistration;
        status = builder.status;
        actionType = builder.actionType;
        warehouseId = builder.warehouseId;
    }


    public static final class Builder {
        private VendorId vendorId;
        private BusinessRegistration businessRegistration;
        private EStatus status;
        private ActionType actionType;
        private WarehouseId warehouseId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder vendorId(VendorId val) {
            vendorId = val;
            return this;
        }

        public Builder businessRegistration(BusinessRegistration val) {
            businessRegistration = val;
            return this;
        }

        public Builder status(EStatus val) {
            status = val;
            return this;
        }

        public Builder actionType(ActionType val) {
            actionType = val;
            return this;
        }
        // for warehouseId
        public Builder warehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Vendor build() {
            return new Vendor(this);
        }
    }
}
