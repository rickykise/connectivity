package ai.fassto.connectivity.domain.purchaseorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.dataaccess.common.valueobject.InventoryCondition;
import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.CustomHeaderCheckListInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.CenterArrivalInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_HHmm;
import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyyMMdd;

@Getter
@AllArgsConstructor
public class PurchaseOrder extends AggregateRoot<PurchaseOrderId> {
    public static final int DEFAULT_PRIORITY_FOR_SOLOCHAIN = 5;
    private PurchaseOrderType type;
    private PurchaseOrderStatus status;
    private PurchaseOrderWorkStatus workStatus;
    private ActionType actionType;

    private LocalDateTime receivedDateTime;
    private WarehouseId warehouseId;
    private CustomerId customerId;
    private CustomHeaderCheckListInfo customHeaderCheckListInfo;
    private List<OrderLine> orderLineList;
    private List<Item> itemList;

    // 센터도착정보
    private CenterArrivalInfo centerArrivalInfo;


    // 입고검수정보
    private VendorId vendorId;
    private String inCheckSlipNo;

    // 입고검수 Damaged 상품 목록
    private List<Item> damagedItemList;

    // 입고확정정보
    private String inSlipNo;
    private VehicleType vehicleType;

    // 입고작업정보
    private InRateType inRateType;
    private Integer workerQty;
    private Integer workTime;
    private Integer payTime;


    /**
     * Completed
     */
    private LocalDate orderDate;
    private LocalDate releasableDate;


    /**
     * Business Logics
     * <p>
     * - centerArrivedDate 센터도착일 세팅
     * - centerArrivedTime 센터도착시간 세팅
     * - CenterArrivalType 센터도착유형(정상도착, 지연도착, 조기도착) 아래 3가지 정보를 참조하여 세팅
     * - ReleaseType 출고유형(당일출고, 익일출고, 정상출고) 세팅
     * 1.출고유형
     * 2.입고일자
     * 3.센터도착시간
     * 당일출고 - 센터도착시간 == 입고일자 00시 이전 -> 조기도착
     * 당일출고 - 센터도착시간 == 입고일자 13시 이전 -> 정상도착
     * 당일출고 - 센터도착시간 == 입고일자 13시 이후 -> 지연도착 -> 출고유형 익일출고로 변환, 16시 이전 도착이면 센터도착유형 정상 처리
     * 익일,d+2일출고 - 센터도착시간 == 입고일자 00시 이전 -> 조기도착
     * 익일,d+2일출고 - 센터도착시간 == 입고일자 16시 이전 -> 정상도착
     * 익일,d+2일출고 - 센터도착시간 == 입고일자 16시 이후 -> 지연도착
     */
    public void arrive(InOrder inOrder) {
        this.centerArrivalInfo = CenterArrivalInfo.builder()
                .centerArrivalDateTime(this.receivedDateTime)
                .centerArrivalType(CenterArrivalType.findTypeWhenArrival(inOrder, this.receivedDateTime))
                .releaseType(ReleaseType.findTypeWhenArrival(inOrder, this.receivedDateTime))
                .build();
        this.orderDate = inOrder.orderDate();
        this.releasableDate = inOrder.releasableDate();
    }

    public void activate(InOrder inOrder) {
        this.workStatus = PurchaseOrderWorkStatus.ACTIVE;
        this.orderDate = inOrder.orderDate();
        this.releasableDate = inOrder.releasableDate();
    }

    public void complete(InOrder inOrder) {
        this.orderDate = inOrder.orderDate();
        this.vendorId = inOrder.vendorId();
        this.releasableDate = inOrder.releasableDate();
        this.centerArrivalInfo = CenterArrivalInfo.builder()
                .releaseType(inOrder.releaseType())
                .centerArrivalType(inOrder.centerArrivalType())
                .centerArrivalDateTime(
                        LocalDateTime.of(
                                DateTimeUtil.stringToLocalDate(inOrder.centerArrivalDate(), DATE_FORMATTER_yyyyMMdd),
                                DateTimeUtil.stringToLocalTime(inOrder.centerArrivalTime(), DATE_FORMATTER_HHmm))
                ).build();
        this.vehicleType = inOrder.vehicleType();
        this.workStatus = PurchaseOrderWorkStatus.COMPLETED;    //입고완료(4)로 변경
        this.payTime = (this.workTime + 9) / 10 * 10;   // 1의자리에서 올림
    }

    public void completeWhenReturn(InOrder inOrder) {
        this.vendorId = inOrder.vendorId();
        this.vehicleType = inOrder.vehicleType();
        this.workStatus = PurchaseOrderWorkStatus.COMPLETED;
    }

    public void cancel() {
        this.workStatus = PurchaseOrderWorkStatus.CANCELLED;
    }

    public Integer updateNewDamagedItemListAndGetListSize(Map<String, Item> foundDamagedItemMap) {
        List<Item> newDamagedItemList = new ArrayList<>();

        for (Item item : findDamagedItemList()) {
            Item foundItem = foundDamagedItemMap.get(item.getReceiptNo());
            if (foundItem == null) {
                newDamagedItemList.add(item);
            }
        }
        this.damagedItemList = newDamagedItemList;

        return this.damagedItemList.size();
    }

    public List<Item> findDamagedItemList() {
        /** Damaged 상품 체크 */
        return itemList.stream()
                .filter(item -> InventoryCondition.UNSERVICEABLE.getSolochainCode().equalsIgnoreCase(item.getCondition()))
                .toList();
    }

    public Integer updateNewDamagedItemListWhenMomoNotBlackAndGetListSize() {
        List<Item> newDamagedItemList = new ArrayList<>();
        for (Item item : damagedItemList) {
            if (StringUtils.isNotBlank(item.getMemo())) {
                newDamagedItemList.add(item);
            }
        }
        this.damagedItemList = newDamagedItemList;

        return this.damagedItemList.size();
    }

    public void completeInCheckProcess(String inCheckSlipNo) {
        this.inCheckSlipNo = inCheckSlipNo; // 전표번호 세팅
    }

    public void completeInProcess(String inSlipNo) {
        this.inSlipNo = inSlipNo; // 전표번호 세팅
    }

    public void completeInWhenReturnProcess(String inSlipNo) {
        this.inSlipNo = inSlipNo; // 전표번호 세팅
    }

    /**
     * [입고 할증률 세팅]
     * 센터도착 시 센터도착구분(CEN_ARV_GBN)값과 출고가능구분(RELEASE_GBN)값이 입고지시(tb_in_ord)테이블에 등록된다.
     * 입고지시 테이블에서 센터도착구분(CEN_ARV_GBN값)과 출고가능구분(RELEASE_GBN)값을 참고하여
     * 9가지의 입고할증률 코드(rateGbn)들 중에 한가지를 가져온 후, 입고작업정보(tb_in_wrk_info)테이블 -> RATE_GBN 컬럼에 등록한다.
     */
    public void completeInWorkProcess() {
        updateInRateTypeByCenterArrivalTime();
    }

    public void completeInWorkWhenReturnProcess(InOrder inOrder) {
        this.orderDate = inOrder.orderDate();
        this.centerArrivalInfo = CenterArrivalInfo.builder()
                .centerArrivalDateTime(this.receivedDateTime)
                .build();
    }

    //센터도착일이 입고일자 기준 -2일 ~ +2일인 경우, 센터도착시간은 16시 기준
    private void updateInRateTypeByCenterArrivalTime() {
        if (this.vehicleType == VehicleType.COURIER && isBetweenPlusAndMinus2DaysAndEveryAt4PM(this.orderDate)) {
            if (this.centerArrivalInfo.releaseType() == ReleaseType.PLUS_DAY_1) {
                this.inRateType = InRateType.RELEASE_PLUS_DAY_1_WHEN_ARRIVAL;
                return;
            } else if (this.centerArrivalInfo.releaseType() == ReleaseType.PLUS_DAY_2) {
                this.inRateType = InRateType.RELEASE_PLUS_DAY_2_WHEN_ARRIVAL;
                return;
            }
        }
        this.inRateType = InRateType.findBy(this.centerArrivalInfo);
    }

    private boolean isBetweenPlusAndMinus2DaysAndEveryAt4PM(LocalDate orderDate) {
        LocalDateTime twoDaysBeforeOrderDateAndAt4PM = LocalDateTime.of(orderDate.minusDays(2), LocalTime.of(16, 0));
        LocalDateTime twoDaysAfterOrderDateAndAt4PM = LocalDateTime.of(orderDate.plusDays(2), LocalTime.of(16, 0));

        return this.centerArrivalInfo.centerArrivalDateTime().isAfter(twoDaysBeforeOrderDateAndAt4PM) &&
                this.centerArrivalInfo.centerArrivalDateTime().isBefore(twoDaysAfterOrderDateAndAt4PM);
    }

    private PurchaseOrder(Builder builder) {
        setId(builder.id);
        type = builder.type;
        status = builder.status;
        workStatus = builder.workStatus;
        actionType = builder.actionType;
        receivedDateTime = builder.receivedDateTime;
        warehouseId = builder.warehouseId;
        customerId = builder.customerId;
        customHeaderCheckListInfo = builder.customHeaderCheckListInfo;
        orderLineList = builder.orderLineList;
        itemList = builder.itemList;
        centerArrivalInfo = builder.centerArrivalInfo;
        vendorId = builder.vendorId;
        inCheckSlipNo = builder.inCheckSlipNo;
        damagedItemList = builder.damagedItemList;
        inSlipNo = builder.inSlipNo;
        vehicleType = builder.vehicleType;
        inRateType = builder.inRateType;
        workerQty = builder.workerQty;
        workTime = builder.workTime;
        orderDate = builder.orderDate;
        releasableDate = builder.releasableDate;
    }

    public static final class Builder {
        private PurchaseOrderId id;
        private PurchaseOrderType type;
        private PurchaseOrderStatus status;
        private PurchaseOrderWorkStatus workStatus;
        private ActionType actionType;
        private LocalDateTime receivedDateTime;
        private WarehouseId warehouseId;
        private CustomerId customerId;
        private CustomHeaderCheckListInfo customHeaderCheckListInfo;
        private List<OrderLine> orderLineList;
        private List<Item> itemList;
        private CenterArrivalInfo centerArrivalInfo;
        private VendorId vendorId;
        private String inCheckSlipNo;
        private List<Item> damagedItemList;
        private String inSlipNo;
        private VehicleType vehicleType;
        private InRateType inRateType;
        private Integer workerQty;
        private Integer workTime;
        private LocalDate orderDate;
        private LocalDate releasableDate;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(PurchaseOrderId val) {
            id = val;
            return this;
        }

        public Builder type(PurchaseOrderType val) {
            type = val;
            return this;
        }

        public Builder status(PurchaseOrderStatus val) {
            status = val;
            return this;
        }

        public Builder workStatus(PurchaseOrderWorkStatus val) {
            workStatus = val;
            return this;
        }

        public Builder actionType(ActionType val) {
            actionType = val;
            return this;
        }

        public Builder receivedDateTime(LocalDateTime val) {
            receivedDateTime = val;
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

        public Builder customHeaderCheckListInfo(CustomHeaderCheckListInfo val) {
            customHeaderCheckListInfo = val;
            return this;
        }

        public Builder orderLineList(List<OrderLine> val) {
            orderLineList = val;
            return this;
        }

        public Builder itemList(List<Item> val) {
            itemList = val;
            return this;
        }

        public Builder centerArrivalInfo(CenterArrivalInfo val) {
            centerArrivalInfo = val;
            return this;
        }

        public Builder vendorId(VendorId val) {
            vendorId = val;
            return this;
        }

        public Builder inCheckSlipNo(String val) {
            inCheckSlipNo = val;
            return this;
        }

        public Builder damagedItemList(List<Item> val) {
            damagedItemList = val;
            return this;
        }

        public Builder inSlipNo(String val) {
            inSlipNo = val;
            return this;
        }



        public Builder vehicleType(VehicleType val) {
            vehicleType = val;
            return this;
        }

        public Builder inRateType(InRateType val) {
            inRateType = val;
            return this;
        }

        public Builder workerQty(Integer val) {
            workerQty = val;
            return this;
        }

        public Builder workTime(Integer val) {
            workTime = val;
            return this;
        }

        public Builder orderDate(LocalDate val) {
            orderDate = val;
            return this;
        }

        public Builder releasableDate(LocalDate val) {
            releasableDate = val;
            return this;
        }

        public PurchaseOrder build() {
            return new PurchaseOrder(this);
        }
    }
}
