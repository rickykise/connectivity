package ai.fassto.connectivity.domain.salesorder.core.entity;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutOrdEntity;
import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.id.SalesOrderId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.*;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderStatus;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderType;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderWorkStatus;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesPickOrderWorkStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public final class SalesOrder extends AggregateRoot<SalesOrderId> {
    public static final int DEFAULT_PRIORITY_FOR_SOLOCHAIN = 5;

    private SalesOrderType type;
    private SalesOrderStatus status;
    private Contact shipToContact; // 수신자
    private Contact senderContact; // 송신자
    private String outWay;
    private VehicleInfo vehicleInfo;
    private List<Document> documentList;
    private String customerBrandedBox;
    private String boxCategory;
    /**
     * 정리되지 않는 필드들
     */
    private String ordDivVal;
    private Integer orderSequence; //  1, - 주문 시퀀스

    private LocalDateTime workDate;
    private WarehouseId warehouseId;
    private Customer customer;
    private List<OrderLine> orderLineList;
    private List<Item> items;
    private List<Item> seperatedItems;
    private List<Consumable> consumables;
    private List<Box> boxes;

    //출고지시확정
    private LocalDate orderDate;
    private Shop shop;
    private String orderNo;
    private boolean hasLotNo;   //출고지시확정때 LotNo을 받은경우 true

    //작업상태
    private SalesOrderWorkStatus workStatus;
    private SalesPickOrderWorkStatus pickOrderWorkStatus;

    private String outSlipNo;
    private ActionType actionType;

    private Integer outType;

    //출고지시확정 취소
    private String allocateYn;   //출고지시 할당 여부

    // 출고 요청 정보
    private OutOrdEntity outOrdEntity;

    public void allocate(OutOrder outOrder) {
        this.orderDate = outOrder.orderDate();  //출고일자
        this.orderNo = outOrder.orderNo();      //주문번호
        this.shop = Shop.Builder.builder().id(outOrder.shopId()).build(); //출고처코드
        this.orderSequence = outOrder.orderSequence(); //주문순번
        this.workStatus = SalesOrderWorkStatus.ON_PROGRESS;
        this.pickOrderWorkStatus = SalesPickOrderWorkStatus.PICKING;
        this.hasLotNo = isHasLotNoAndQty();
    }

    public void completePicking(OutOrder outOrder, boolean isExist) {
        this.orderDate = outOrder.orderDate();  //출고일자
        this.orderNo = outOrder.orderNo();      //주문번호
        this.shop = Shop.Builder.builder().id(outOrder.shopId()).build(); //출고처코드
        this.orderSequence = outOrder.orderSequence(); //주문순번
        this.pickOrderWorkStatus = SalesPickOrderWorkStatus.PICK_COMPLETE; //피킹완료(2)
        this.hasLotNo = isExist;     //출고지시확정시(ALLOCATED) LotNo를 받는경우 true
    }

    public void completePacking(OutOrder outOrder) {
        this.orderNo = outOrder.orderNo();      //주문번호
        this.shop = Shop.Builder.builder().id(outOrder.shopId()).build(); //출고처코드
        this.outType = outOrder.outType();
        this.orderSequence = outOrder.orderSequence(); //주문순번
        this.pickOrderWorkStatus = SalesPickOrderWorkStatus.PACK_COMPLETE; //검수패킹완료(3)
    }

    public void completeShipping(OutOrder outOrder, String outSlipNo) {
        this.orderNo = outOrder.orderNo();      //주문번호
        this.shop = Shop.Builder.builder().id(outOrder.shopId()).build(); //출고처코드
        this.outType = outOrder.outType();
        this.orderSequence = outOrder.orderSequence(); //주문순번
        this.workStatus = SalesOrderWorkStatus.COMPLETED; //출고완료(3)
        this.pickOrderWorkStatus = SalesPickOrderWorkStatus.SALES_COMPLETE; //출고완료(4)
        this.outSlipNo = outSlipNo;
    }

    public void seperatedItems(List<Item> seperatedItems) {
        this.seperatedItems = seperatedItems;
    }

    public void allocateCancel(OutOrder outOrder) {
        this.orderNo = outOrder.orderNo();      //주문번호
        this.workStatus = SalesOrderWorkStatus.REQUEST; //출고요청(1)
    }

    public void processForOutPickMap(String allocateYn) {
        this.allocateYn = allocateYn;
    }

    public boolean isHasLotNoAndQty() {
        for (Item item : items) {
            if (StringUtils.isBlank(item.getLot()) || item.getQty() == null){
                return false;
            }
        }
        return true;
    }


    private SalesOrder(Builder builder) {
        setId(builder.id);
        type = builder.type;
        status = builder.status;
        orderLineList = builder.orderLineList;
        shipToContact = builder.shipToContact;
        senderContact = builder.senderContact;
        outWay = builder.outWay;
        ordDivVal = builder.ordDivVal;
        orderSequence = builder.orderSequence;
        workDate = builder.workDate;
        warehouseId = builder.warehouseId;
        customer = builder.customer;
        items = builder.items;
        consumables = builder.consumables;
        boxes = builder.boxes;
        orderDate = builder.orderDate;
        orderNo = builder.orderNo;
        actionType = builder.actionType;
        vehicleInfo = builder.vehicleInfo;
        documentList = builder.documentList;
        customerBrandedBox = builder.customerBrandedBox;
        boxCategory = builder.boxCategory;
    }

    public static final class Builder {
        private SalesOrderId id;
        private SalesOrderType type;
        private SalesOrderStatus status;
        private List<OrderLine> orderLineList;
        private Contact shipToContact;
        private Contact senderContact;
        private String outWay;
        private String ordDivVal;
        private Integer orderSequence;
        private LocalDateTime workDate;
        private WarehouseId warehouseId;
        private Customer customer;
        private List<Item> items;
        private List<Consumable> consumables;
        private List<Box> boxes;
        private LocalDate orderDate;
        private String orderNo;
        private ActionType actionType;
        private VehicleInfo vehicleInfo;
        private List<Document> documentList;
        private String customerBrandedBox;
        private String boxCategory;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(SalesOrderId val) {
            id = val;
            return this;
        }

        public Builder type(SalesOrderType val) {
            type = val;
            return this;
        }

        public Builder status(SalesOrderStatus val) {
            status = val;
            return this;
        }

        public Builder orderLineList(List<OrderLine> val) {
            orderLineList = val;
            return this;
        }

        public Builder shipToContact(Contact val) {
            shipToContact = val;
            return this;
        }

        public Builder senderContact(Contact val) {
            senderContact = val;
            return this;
        }

        public Builder outWay(String val) {
            outWay = val;
            return this;
        }

        public Builder ordDivVal(String val) {
            ordDivVal = val;
            return this;
        }

        public Builder orderSequence(Integer val) {
            orderSequence = val;
            return this;
        }

        public Builder workDate(LocalDateTime val) {
            workDate = val;
            return this;
        }

        public Builder warehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder customer(Customer val) {
            customer = val;
            return this;
        }

        public Builder items(List<Item> val) {
            items = val;
            return this;
        }

        public Builder consumables(List<Consumable> val) {
            consumables = val;
            return this;
        }

        public Builder boxes(List<Box> val) {
            boxes = val;
            return this;
        }

        public Builder orderDate(LocalDate val) {
            orderDate = val;
            return this;
        }


        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder actionType(ActionType val) {
            actionType = val;
            return this;
        }
        public Builder vehicleInfo(VehicleInfo val) {
            vehicleInfo = val;
            return this;
        }

        public Builder documentList(List<Document> val) {
            documentList = val;
            return this;
        }

        public Builder customerBrandedBox(String val) {
            customerBrandedBox = val;
            return this;
        }

        public Builder boxCategory(String val) {
            boxCategory = val;
            return this;
        }

        public SalesOrder build() {
            return new SalesOrder(this);
        }
    }
}
