package ai.fassto.connectivity.domain.item.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.common.valueobject.Size;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.VendorId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.item.core.valueobject.DistributionTerm;
import ai.fassto.connectivity.domain.item.core.valueobject.ItemCategory;
import ai.fassto.connectivity.domain.item.core.valueobject.TemperatureManager;
import lombok.Getter;

@Getter
public class Item extends AggregateRoot<ItemId> {

    private String name; // godNm; // ": "[테스트]로션",
    private String barcode; // private String godBarcd; // ": "",
    private Size size;
    private ActionType actionType;
    private CustomerId customerId; // cstCd; // ": "04233",
    private VendorId vendorId; //  supCd; // ": "04233001",
    private ItemCategory itemCategory;
    private DistributionTerm distributionTerm; // 유통기간 관련
    private Boolean makeDateManaged;
    private String completedBox;
    private TemperatureManager temperatureManager;
    private String cushioningMaterialOption;
    private String cushioningMaterialOptionName;
    private String materialClassification;
    private String materialClassificationName;
    private String subsidiaryMaterialsType;
    private String subsidiaryMaterialsTypeName;
    private WarehouseId warehouseId;


    private Item(Builder builder) {
        setId(builder.id);
        name = builder.name;
        barcode = builder.barcode;
        size = builder.size;
        actionType = builder.actionType;
        customerId = builder.customerId;
        vendorId = builder.vendorId;
        itemCategory = builder.itemCategory;
        distributionTerm = builder.distributionTerm;
        completedBox = builder.completedBox;
        temperatureManager = builder.temperatureManager;
        cushioningMaterialOption = builder.cushioningMaterialOption;
        cushioningMaterialOptionName = builder.cushioningMaterialOptionName;
        materialClassification = builder.materialClassification;
        materialClassificationName = builder.materialClassificationName;
        subsidiaryMaterialsType = builder.subsidiaryMaterialsType;
        subsidiaryMaterialsTypeName = builder.subsidiaryMaterialsTypeName;
        makeDateManaged = builder.makeDateManaged;
        warehouseId = builder.warehouseId;
    }


    public static final class Builder {
        private ItemId id;
        private String name;
        private String barcode;
        private Size size;
        private ActionType actionType;
        private CustomerId customerId;
        private VendorId vendorId;
        private ItemCategory itemCategory;
        private DistributionTerm distributionTerm;
        private Boolean makeDateManaged;
        private String completedBox;
        private TemperatureManager temperatureManager;
        private String cushioningMaterialOption;
        private String cushioningMaterialOptionName;
        private String materialClassification;
        private String materialClassificationName;
        private String subsidiaryMaterialsType;
        private String subsidiaryMaterialsTypeName;
        private WarehouseId warehouseId;

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

        public Builder barcode(String val) {
            barcode = val;
            return this;
        }

        public Builder size(Size val) {
            size = val;
            return this;
        }

        public Builder actionType(ActionType val) {
            actionType = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder vendorId(VendorId val) {
            vendorId = val;
            return this;
        }

        public Builder itemCategory(ItemCategory val) {
            itemCategory = val;
            return this;
        }

        public Builder distributionTerm(DistributionTerm val) {
            distributionTerm = val;
            return this;
        }

        public Builder completedBox(String val) {
            completedBox = val;
            return this;
        }

        public Builder temperatureManager(TemperatureManager temperatureManager) {
            this.temperatureManager = temperatureManager;
            return this;
        }

        public Builder cushioningMaterialOption(String cushioningMaterialOption) {
            this.cushioningMaterialOption = cushioningMaterialOption;
            return this;
        }

        public Builder cushioningMaterialOptionName(String cushioningMaterialOptionName) {
            this.cushioningMaterialOptionName = cushioningMaterialOptionName;
            return this;
        }

        public Builder materialClassification(String materialClassification) {
            this.materialClassification = materialClassification;
            return this;
        }

        public Builder materialClassificationName(String materialClassificationName) {
            this.materialClassificationName = materialClassificationName;
            return this;
        }

        public Builder subsidiaryMaterialsType(String subsidiaryMaterialsType) {
            this.subsidiaryMaterialsType = subsidiaryMaterialsType;
            return this;
        }

        public Builder subsidiaryMaterialsTypeName(String subsidiaryMaterialsTypeName) {
            this.subsidiaryMaterialsTypeName = subsidiaryMaterialsTypeName;
            return this;
        }

        public Builder makeDateManaged(Boolean makeDateManaged) {
            this.makeDateManaged = makeDateManaged;
            return this;
        }

        // for warehouseId
        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }


        public Item build() {
            return new Item(this);
        }
    }
}
