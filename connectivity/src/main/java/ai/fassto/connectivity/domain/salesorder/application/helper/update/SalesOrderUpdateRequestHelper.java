package ai.fassto.connectivity.domain.salesorder.application.helper.update;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.dataaccess.salesorder.entity.OutOrdSeperatedGodQtyEntity;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.salesorder.application.port.output.repository.SalesOrderRepository;
import ai.fassto.connectivity.domain.salesorder.core.entity.*;
import ai.fassto.connectivity.domain.salesorder.core.exception.*;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.*;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ai.fassto.connectivity.dataaccess.common.valueobject.SlipNoDivType.OUT;
import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.OutType.CARRY_OUT;
import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesOrderWorkStatus.ON_PROGRESS;
import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.SalesPickOrderWorkStatus.PICKING;
import static ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.OutWay.EXPIRATION_DATE;

@Slf4j
@RequiredArgsConstructor
public abstract class SalesOrderUpdateRequestHelper {
    protected final SalesOrderRepository salesOrderRepository;

    public abstract boolean is(OrderType orderType);

    public abstract void persistWhenAllocated(SalesOrder salesOrder);

    public abstract void persistWhenPickingCompleted(SalesOrder salesOrder);

    public abstract void persistWhenPackingCompleted(SalesOrder salesOrder);

    public abstract void persistWhenShippingCompleted(SalesOrder salesOrder);

    public abstract void persistWhenCancelAllocated(SalesOrder salesOrder);

    OutOrder checkOutOrder(SalesOrder salesOrder) {
        /* 출고 주문 정보 1건 조회 findOneOutOrd */
        Optional<OutOrder> outOrderOptional = salesOrderRepository.findOneOutOrdOptional(salesOrder);
        if (outOrderOptional.isEmpty()) {
            throw new SalesOrderNotFoundException(new ErrorDetail("slipNo", salesOrder.getId().getValue()));
        }

        return outOrderOptional.get();
    }

    void checkItems(List<OrderLine> orderLineList) {
        if (orderLineList == null || orderLineList.size() == 0) {
            return;
        }

        List<ItemId> itemIdList = orderLineList.stream().map(orderLine -> orderLine.getItemSimple().getId()).toList();
        List<ItemSimple> foundItemList = salesOrderRepository.findItemSimplesBy(itemIdList);
        Map<ItemId, ItemSimple> foundItemMap = foundItemList.stream().collect(Collectors.toMap(ItemSimple::getId, Function.identity()));

        for (ItemId id : itemIdList) {
            ItemSimple itemSimple = foundItemMap.get(id);
            if (itemSimple == null) {
                throw new SalesOrderItemNotFoundException(new ErrorDetail("itemId", id.getValue()));
            } else if (!itemSimple.getEnabled()) {
                throw new SalesOrderItemDisabledException(new ErrorDetail("itemId", id.getValue()));
            }
        }

    }

    //가할당된 lotNo값 하고 맞는지 확인
    void checkInventoryAssign(SalesOrder salesOrder, OutOrder outOrder) {
        //유통기한 지정출고 outWay(3)이면서 반출 outTp(4)이 아닌것만 체크
        if(OutWay.findByErpCode(outOrder.outWay()) == EXPIRATION_DATE && OutType.findByErpCode(outOrder.outType()) != CARRY_OUT) {
            List<OutInventoryAssign> outInventoryAssignList = salesOrderRepository.findOutInventoryAssignList(salesOrder);
            List<Item> itemList = salesOrder.getItems();
            for(Item item : itemList) {
                OutInventoryAssign outInventoryAssign = OutInventoryAssign.builder()
                                .itemSeq(item.getSequence())
                                .godCd(item.getCode())
                                .lotNo(item.getLot())
                                .build();

                if (!outInventoryAssignList.contains(outInventoryAssign)) {
                    log.error("[SalesOrder] Lot Number is mis match. Please check again outInventoryAssign table, outOrdSlipNo: {}", salesOrder.getId().getValue());
                    //throw new SalesOrderLotMisMatchException(new ErrorDetail("outOrdSlipNo", salesOrder.getId().getValue()));
                }
            }
        }
    }

    void checkBoxes(SalesOrder salesOrder) {
        if (CollectionUtils.isEmpty(salesOrder.getBoxes())) {
            return;
        }

        for(Box box: salesOrder.getBoxes()) {
            if (BoxId.PALLET_DISP.getSolochainCode().equals(box.getBoxId())) {
                throw new SalesOrderNotSupportException(new ErrorDetail("BoxId", box.getBoxId()));
            }
        }
    }

    void checkConsumables(SalesOrder salesOrder) {
        if (CollectionUtils.isEmpty(salesOrder.getConsumables())) {
            return;
        }

        //창고 부자재 목록
        List<Consumable> wareHouseConsumableList = salesOrderRepository.findWareHouseConsumableList(salesOrder);
        for(Consumable consumable: salesOrder.getConsumables()) {
            if(wareHouseConsumableList.contains(consumable)) {
                Consumable foundConsumable = wareHouseConsumableList.get(wareHouseConsumableList.indexOf(consumable));
                checkConsumableEnable(foundConsumable);
                consumable.setId(foundConsumable.getId());
                continue;
            }
            throw new SalesOrderWareHouseConsumableNotFoundException(new ErrorDetail("tb_wh_subsidiary", consumable.toString()));
        }
    }

    List<Item> separateGod(SalesOrder salesOrder) {
        List<Item> seperatedItems = new ArrayList<>();
        List<OutOrdSeperatedGodQtyEntity> outOrdSeperatedGodQtyEntityList =
                salesOrderRepository.findSeperatedGodQtyList(salesOrder);

        for (OutOrdSeperatedGodQtyEntity outOrdSeperatedGodQtyEntity : outOrdSeperatedGodQtyEntityList) {
            String godCd = outOrdSeperatedGodQtyEntity.getGodCd();
            int mainGodOrdQty = outOrdSeperatedGodQtyEntity.getMainGodOrdQty();
            int addGodOrdQty = outOrdSeperatedGodQtyEntity.getAddGodOrdQty();
            int promotionGodOrdQty = outOrdSeperatedGodQtyEntity.getPromotionGodOrdQty();

            for (Item item : salesOrder.getItems()) {
                if (godCd.equals(item.getCode())) {
                    mainGodOrdQty -= countGodOrdQtyAfterSeparate(item, seperatedItems, mainGodOrdQty, "01");
                    addGodOrdQty -= countGodOrdQtyAfterSeparate(item, seperatedItems, addGodOrdQty, "02");
                    promotionGodOrdQty -= countGodOrdQtyAfterSeparate(item, seperatedItems, promotionGodOrdQty, "03");
                }
            }
        }

        return seperatedItems;
    }

    private int countGodOrdQtyAfterSeparate(Item item, List<Item> seperatedItems, int godOrdQty, String godDiv) {
        if (godOrdQty > 0 && item.getQty() > 0) {
            int outQty = Math.min(godOrdQty, item.getQty());
            seperatedItems.add(addSeperatedItem(item,godDiv, outQty));
            return outQty;
        }

        return 0;
    }

    private static Item addSeperatedItem(Item item, String godDiv, int outQty) {
        Item separatedItem = Item.builder()
                .receiptNo(item.getReceiptNo())
                .sequence(item.getSequence())
                .code(item.getCode())
                .qty(outQty)
                .status(item.getStatus())
                .memo(item.getMemo())
                .loc(item.getLoc())
                .lu(item.getLu())
                .lot(item.getLot())
                .expiryDate(item.getExpiryDate())
                .boxSeq(item.getBoxSeq())
                .godDiv(godDiv)
                .build();

        return separatedItem;
    }

    void checkOutPickMap(SalesOrder salesOrder) {
        if (!isExistOutPickMap(salesOrder)) {
            throw new SalesOrderPickMapNotFoundException(new ErrorDetail("outOrdSlipNo", salesOrder.getId().getValue()));
        }
    }

    //출고지시확정 단계인지 확인
    void checkAllocate(SalesOrder salesOrder, OutOrder outOrder) {
        Optional<OutPickOrder> outPickOrderOptional = salesOrderRepository.findOneOutPickOrdOptional(salesOrder);

        if(outPickOrderOptional.isEmpty()) {
            if(!(SalesOrderWorkStatus.findByErpCode(outOrder.workStatus()) == ON_PROGRESS)) {
                throw new SalesOrderAllocateCancelledException(new ErrorDetail("outOrdSlipNo", salesOrder.getId().getValue()));
            }
        }
        else {
            if(!(SalesOrderWorkStatus.findByErpCode(outOrder.workStatus()) == ON_PROGRESS &&
                 SalesPickOrderWorkStatus.findByErpCode(outPickOrderOptional.get().workStatus()) == PICKING)) {
                throw new SalesOrderAllocateCancelledException(new ErrorDetail("outOrdSlipNo", salesOrder.getId().getValue()));
            }
        }
    }

    boolean isExistOutPickMap(SalesOrder salesOrder) {
        /* 출고지시연계 정보 존재여부 확인 */
        return salesOrderRepository.isExistOutPickMap(salesOrder);
    }

    boolean isExistOutOrdSet(SalesOrder salesOrder) {
        /* 출고지시확정 정보 존재여부 확인 */
        return salesOrderRepository.isExistOutOrdSet(salesOrder);
    }

    boolean isExistOutPickOrder(SalesOrder salesOrder) {
        /* 출고피킹지시 정보 존재여부 확인 */
        return salesOrderRepository.isExistOutPickOrd(salesOrder);
    }

    boolean isExistOutPack(SalesOrder salesOrder) {
        /* 출고패킹 정보 존재여부 확인 */
        return salesOrderRepository.isExistOutPack(salesOrder);
    }

    boolean isExistOutInvoice(SalesOrder salesOrder) {
        /* 출고완료 invoice 정보 존재여부 확인 */
        return salesOrderRepository.isExistOutInvoice(salesOrder);
    }

    boolean isExistOut(SalesOrder salesOrder) {
        /* 출고완료 정보 존재여부 확인 */
        return salesOrderRepository.isExistOut(salesOrder);
    }

    boolean isExistOutPackWhSub(SalesOrder salesOrder) {
        /* 출고패킹 센터 부자재 정보 존재여부 확인 */
        return salesOrderRepository.isExistOutPackWhSub(salesOrder);
    }

    /**
     * 출고 가할당 프로세스
     */
    public void processForOutInventoryAssignWhenAllocated(SalesOrder salesOrder) {
        //출고지시확정때 LotNo을 받은경우
        if(salesOrder.isHasLotNo()) {
            /* 가할당 정보 삭제 deleteOutInventoryAssign */
            salesOrderRepository.deleteOutInventoryAssign(salesOrder);

            /* 가할당 등록 insertBulkOutInventoryAssign */
            salesOrderRepository.insertBulkOutInventoryAssign(salesOrder);
        }
    }

    /**
     * 출고지시연계 프로세스
     */
    public void processForOutPickMapWhenAllocated(SalesOrder salesOrder) {
        // 출고지시할당 여부 Y
        salesOrder.processForOutPickMap("Y");

        if (isExistOutPickMap(salesOrder)) {
            /* 출고지시연계 수정 updateOutPickMap */
            salesOrderRepository.updateOutPickMap(salesOrder);
        } else {
            /* 출고지시연계 생성 insertOutPickMap */
            salesOrderRepository.insertOutPickMap(salesOrder);
        }
    }

    /**
     * 출고지시확정 프로세스(출고지시확정)
     */
    public void processForOutOrderSetWhenAllocated(SalesOrder salesOrder) {
        //출고지시확정때 LotNo을 받은경우
        if(salesOrder.isHasLotNo()) {
            /* 출고지시확정 생성 insertBulkOutOrdSet */
            salesOrderRepository.insertBulkOutOrdSet(salesOrder);
        }
    }

    /**
     * 피킹지시 프로세스(출고지시확정)
     */
    public void processForOutPickOrderWhenAllocated(SalesOrder salesOrder) {
        //출고지시확정때 LotNo을 받은경우
        if(salesOrder.isHasLotNo()) {
            /* 피킹순번 조회 getPickSeq */
            int pickSeq = salesOrderRepository.getPickSeq(salesOrder);

            /* 출고피킹오더 생성 insertBulkOutPickOrd */
            salesOrderRepository.insertBulkOutPickOrd(salesOrder, pickSeq);
        }
    }

    /**
     * 출고지시확정 프로세스(피킹)
     */
    public void processForOutOrderSetWhenPicked(SalesOrder salesOrder) {
        //출고지시확정때 LotNo를 받은경우
        if(!isExistOutPickMap(salesOrder)) { //출고지시확정때 LotNo를 못 받은경우에 생성
            /* 출고지시확정 생성 insertBulkOutOrdSet */
            salesOrderRepository.insertBulkOutOrdSet(salesOrder);
        }
    }

    /**
     * 피킹지시 프로세스(피킹)
     */
    public void processForOutPickOrderWhenPicked(SalesOrder salesOrder, boolean isExist) {
        //출고지시확정때 LotNo을 받은경우
        if(isExist) {
            /* 피킹완료 수량 세팅 updateOutPickOrdPickQty */
            salesOrderRepository.updateBulkOutPickOrdPickQty(salesOrder);

            /* 작업상태 변경 피킹작업중(1) -> 피킹완료(2)  */
            salesOrderRepository.updateOutPickOrdStatus(salesOrder);
        } else {
            /* 피킹순번 조회 getPickSeq */
            int pickSeq = salesOrderRepository.getPickSeq(salesOrder);

            /* 출고피킹오더 생성 insertBulkOutPickOrd */
            salesOrderRepository.insertBulkOutPickOrd(salesOrder, pickSeq);
        }
    }

    /**
     * 피킹지시 프로세스(패킹완료)
     */
    public void processForOutPickOrderWhenPackedOrShipped(SalesOrder salesOrder) {
        /* 패킹수량 수정 */
        salesOrderRepository.updateBulkOutPickOrdPackQty(salesOrder);

        /* 출고피킹 작업상태 수정 */
        salesOrderRepository.updateOutPickOrdStatus(salesOrder);

    }

    /**
     * 패킹 프로세스
     */
    public void processForOutPackWhenPacked(SalesOrder salesOrder) {
        //패킹정보 있으면 삭제
        if (isExistOutPack(salesOrder)) {
            /* 출고패킹 정보 삭제 deleteOutPack */
            salesOrderRepository.deleteOutPack(salesOrder);
        }

        /* 출고패킹 정보 생성 insertBulkOutPack */
        salesOrderRepository.insertBulkOutPack(salesOrder);
    }
    public void processForOutOrderWhenPacked(SalesOrder salesOrder, OutOrder outOrder){
        updateOutOrdParcelCdInvoiceNo(salesOrder, outOrder);
        updateOutOrdDivWhenPacked(salesOrder);
    }

    /**
     * 출고 프로세스
     */
    public void processForOutWhenShipped(SalesOrder salesOrder) {
        /* 출고완료 정보 생성 insertBulkOut */
        salesOrderRepository.insertBulkOut(salesOrder);
    }

    public void processForOutInvoiceWhenShipped(SalesOrder salesOrder) {
        /* 출고완료 invoice 생성 insertBulkOutInvoice */
        salesOrderRepository.insertBulkOutInvoice(salesOrder);
    }

    public void processForOutOrderWhenShipped(SalesOrder salesOrder, OutOrder outOrder){
        updateOutOrdDivWhenShipped(salesOrder, outOrder);
        updateOutOrdParcelCdInvoiceNo(salesOrder, outOrder);
        changeWorkStatusOutOrder(salesOrder);
    }

    public void processForOutConsumableWhenShipped(SalesOrder salesOrder) {
        if (CollectionUtils.isEmpty(salesOrder.getConsumables())) {
            return;
        }
        salesOrderRepository.insertBulkOutPackWhSub(salesOrder);
    }

    public void processForOutPackWhenShipped(SalesOrder salesOrder) {
        //패킹정보 있으면 삭제
        if (isExistOutPack(salesOrder)) {
            /* 출고패킹 정보 삭제 deleteOutPack */
            salesOrderRepository.deleteOutPack(salesOrder);
        }

        /* 출고패킹 정보 생성 insertBulkOutPack */
        salesOrderRepository.insertBulkOutPack(salesOrder);
    }


    /**
     * 출고요청 작업 상태 변경
     */
    public void changeWorkStatusOutOrder(SalesOrder salesOrder) {
        /* 출고 작업 상태 변경 UpdateWrkStat */
        salesOrderRepository.updateOutOrdStatus(salesOrder);
    }

    /**
     * 택배사 코드, 송장번호 수정 parcelCd(택배사 코드), invoiceNo(송장번호)
     */
    public void updateOutOrdParcelCdInvoiceNo(SalesOrder salesOrder, OutOrder outOrder) {
        /* 택배사 송장번호 수정 */
        if (OutDiv.COURIER.equals(OutDiv.findByErpCode(outOrder.outDiv()))) {
            salesOrderRepository.updateOutOrdParcelCdInvoiceNo(salesOrder);
        }
    }

    public void updateOutPackParcelCdInvoiceNo(SalesOrder salesOrder, OutOrder outOrder) {
        /* 택배사 송장번호 수정 */
        if (OutDiv.COURIER.equals(OutDiv.findByErpCode(outOrder.outDiv()))) {
            salesOrderRepository.updateOutPackParcelCdInvoiceNo(salesOrder);
        }
    }

    /**
     * 체인로지스일 때 ORD_DIV - "D"(원데이 배송)로 변경 (패킹시)
     */
    public void updateOutOrdDivWhenPacked(SalesOrder salesOrder) {
        if(CourierType.CHAINLOGIS.getSolochainCode().equals(salesOrder.getBoxes().get(0).getCourierCode())) {
            salesOrderRepository.updateOutOrdOrdDiv(salesOrder, OrdDiv.ONE_DAY.getErpCode());
        }
    }

    /**
     * 체인로지스일 때 ORD_DIV - "D"(원데이 배송)로 변경 (출고시)
     */
    public void updateOutOrdDivWhenShipped(SalesOrder salesOrder, OutOrder outOrder) {
        //패킹시에 원데이 배송으로 변경 안했으면 출고시에 변경
        if(CourierType.CHAINLOGIS.getSolochainCode().equals(salesOrder.getBoxes().get(0).getCourierCode()) &&
           !CourierType.CHAINLOGIS.getErpCode().equals(outOrder.parcelCd()) && StringUtils.isBlank(outOrder.invoiceNo())
        ) {
            salesOrderRepository.updateOutOrdOrdDiv(salesOrder, OrdDiv.ONE_DAY.getErpCode());
        }
    }

    public void processForOutPickMapWhenCancelAllocated(SalesOrder salesOrder) {
        /* 출고 지시 연계 상태 N으로 변경 */
        salesOrder.processForOutPickMap("N");
        salesOrderRepository.updateOutPickMapAllocateYn(salesOrder);
    }

    public void processForOutOrderSetWhenCancelAllocated(SalesOrder salesOrder) {
        if (isExistOutOrdSet(salesOrder)) {
            /* 출고지시확정 정보 삭제 */
            salesOrderRepository.deleteOutOrdSet(salesOrder);
        }
    }

    public void processForOutPickOrderWhenCancelAllocated(SalesOrder salesOrder) {
        if (isExistOutPickOrder(salesOrder)) {
            /* 출고피킹지시 정보 삭제 */
            salesOrderRepository.deleteOutPickOrd(salesOrder);
        }
    }

    public void processForOutPackWhenCancelAllocated(SalesOrder salesOrder) {
        if (isExistOutPack(salesOrder)) {
            /* 출고패킹 정보 삭제 */
            salesOrderRepository.deleteOutPack(salesOrder);
        }
    }

    public void processForOutWhenCancelAllocated(SalesOrder salesOrder) {
        if (isExistOut(salesOrder)) {
            /* 출고완료 정보 삭제 */
            salesOrderRepository.deleteOut(salesOrder);
        }
    }

    public void processForOutInvoiceWhenCancelAllocated(SalesOrder salesOrder) {
        if (isExistOutInvoice(salesOrder)) {
            /* 출고완료 invoice 정보 삭제 deleteOutInvoice */
            salesOrderRepository.deleteOutInvoice(salesOrder);
        }
    }
    public void processForOutOrderWhenCancelAllocated(SalesOrder salesOrder) {
        /* 출고 작업 상태 변경 UpdateWrkStat */
        salesOrderRepository.updateOutOrdStatus(salesOrder); //출고작업중(2) -> 출고요청(1)

        /* 택배사코드 ParcelCd, 송장번호 InvoiceNo null값으로 변경 */
        salesOrderRepository.updateOutOrdParcelCdInvoiceNoToNull(salesOrder);
    }

    public void processForOutConsumableWhenCancelAllocated(SalesOrder salesOrder) {
        if (isExistOutPackWhSub(salesOrder)) {
            /* 출고패킹 센터 부자재 정보 삭제 */
            salesOrderRepository.deleteOutPackWhSub(salesOrder);
        }

    }

    public String generateOutSlipNo(SalesOrder salesOrder) {
        return salesOrderRepository.getSlipNo(salesOrder, OUT.getErpCode());
    }

    private void checkConsumableEnable(Consumable consumable) {
        if (!"Y".equals(consumable.getUseYn())) {
            throw new SalesOrderItemDisabledException(new ErrorDetail("tb_wh_subsidiary", consumable.toString()));
        }
    }

}
