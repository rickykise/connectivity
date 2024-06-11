package ai.fassto.connectivity.dataaccess.purchaseorder.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.VendorId;
import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.dataaccess.common.dto.LocNoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.SlipNoDTO;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import ai.fassto.connectivity.dataaccess.purchaseorder.dto.*;
import ai.fassto.connectivity.dataaccess.purchaseorder.entity.*;
import ai.fassto.connectivity.dataaccess.common.valueobject.InventoryCondition;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.CustomHeaderCheckListInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Item;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.CenterArrivalInfo;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyyMMdd;
import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss;

@Component
public class PurchaseOrderDataAccessMapper {

    public InOrdEntity purchaseOrderToInOrdEntity(PurchaseOrder purchaseOrder) {
        return InOrdEntity.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .wrkStat(purchaseOrder.getWorkStatus().getErpCode())
                .build();
    }

    public InOrdEntity purchaseOrderToInOrdEntityWhenArrival(PurchaseOrder purchaseOrder) {
        CenterArrivalInfo centerArrivalInfo = purchaseOrder.getCenterArrivalInfo();

        return InOrdEntity.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .cenArvDt(centerArrivalInfo.getArrivalDateAsString())
                .cenArvTime(centerArrivalInfo.getArrivalTimeAsString())
                .cenArvGbn(centerArrivalInfo.centerArrivalType().getErpCode())
                .releaseGbn(centerArrivalInfo.releaseType().getErpCode())
                .build();
    }

    public InWrkInfoEntity purchaseOrderToInWrkInfoEntityWhenSales(PurchaseOrder purchaseOrder) {
        CustomHeaderCheckListInfo customHeaderCheckListInfo = purchaseOrder.getCustomHeaderCheckListInfo();
        return InWrkInfoEntity.builder()
                .ordDt(DateTimeUtil.localDateToString(purchaseOrder.getOrderDate(), DATE_FORMATTER_yyyyMMdd))
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getId().getValue())
                .seq(purchaseOrder.getItemList().get(0).getOrderLineId().getValue()) //검수순번 sequence로 고정
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .inWrkPer(purchaseOrder.getWorkerQty())
                .inWrkTime(purchaseOrder.getWorkTime())
                .payTime(purchaseOrder.getPayTime())
                .pltCnt(customHeaderCheckListInfo.getPltCnt())
                .weight5(customHeaderCheckListInfo.getWeight5())
                .weight10(customHeaderCheckListInfo.getWeight10())
                .weight15(customHeaderCheckListInfo.getWeight15())
                .weight20(customHeaderCheckListInfo.getWeight20())
                .weight20Over(customHeaderCheckListInfo.getWeight20Over())
                .subBoxCnt(customHeaderCheckListInfo.getSubBoxCnt())
                .mixInCnt(customHeaderCheckListInfo.getMixInCnt())
                .mixInYn(customHeaderCheckListInfo.getMixInCnt().intValue() > 0 ? "Y" : "N")
                .rateGbn(purchaseOrder.getInRateType().getErpCode())
                .build();
    }

    public InWrkInfoEntity purchaseOrderToInWrkInfoEntityWhenReturn(PurchaseOrder purchaseOrder) {
        CustomHeaderCheckListInfo customHeaderCheckListInfo = purchaseOrder.getCustomHeaderCheckListInfo();
        return InWrkInfoEntity.builder()
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .ordDt(DateTimeUtil.localDateToString(purchaseOrder.getOrderDate(), DATE_FORMATTER_yyyyMMdd))         //yyyy-MM-dd -> yyyyMMdd
                .inArvDt(purchaseOrder.getCenterArrivalInfo().getArrivalDateAsString())
                .inArvTime("0000")
                .pltCnt(customHeaderCheckListInfo.getPltCnt())
                .weight5(customHeaderCheckListInfo.getWeight5())
                .weight10(customHeaderCheckListInfo.getWeight10())
                .weight15(customHeaderCheckListInfo.getWeight15())
                .weight20(customHeaderCheckListInfo.getWeight20())
                .weight20Over(customHeaderCheckListInfo.getWeight20Over())
                .subBoxCnt(customHeaderCheckListInfo.getSubBoxCnt())
                .mixInCnt(customHeaderCheckListInfo.getMixInCnt())
                .inRtnPayCd(customHeaderCheckListInfo.getReturnCollectWay())
                .inRtnPay(customHeaderCheckListInfo.getFreightCost())
                /* (request 정보 확인)
                .inWrkPer(inWrkInfo.getInWrkPer())
                .inWrkTime(inWrkInfo.getInWrkTime())
                .inWrkCd(inWrkInfo.getInWrkCd())*/
                .build();
    }

    public InCheckBulkInsertDTO purchaseOrderToInCheckBulkInsertDTO(PurchaseOrder purchaseOrder) {
        CustomHeaderCheckListInfo customHeaderCheckListInfo = purchaseOrder.getCustomHeaderCheckListInfo();
        return InCheckBulkInsertDTO.builder()
                .inDt(DateTimeUtil.localDateTimeToString(purchaseOrder.getReceivedDateTime(), DATE_FORMATTER_yyyyMMdd))
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getInCheckSlipNo())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .supCd(purchaseOrder.getVendorId().getValue())
                .inDiv(OrderType.SALES.getErpCode()) // 입고 구분 (1:입고, 2:반품) 1고정
                .inWay(purchaseOrder.getVehicleType().getErpCode())  // 입고 방식 (01:택배, 02:차량)
                .inOrdSlipNo(purchaseOrder.getId().getValue())
                .inOrdSeq(purchaseOrder.getItemList().get(0).getOrderLineId().getValue())// 검수순번 sequence값으로 고정
                .inTp(InType.NORMAL.getCode())  // 입고 유형 (1:정상, 2:고객사재고이관, 9:예외)
                .pltCnt(customHeaderCheckListInfo.getPltCnt())
                .inWrkPer(purchaseOrder.getWorkerQty())
                .inWrkTime(purchaseOrder.getWorkTime())
                .payTime(purchaseOrder.getPayTime())
                .weight5(customHeaderCheckListInfo.getWeight5())
                .weight10(customHeaderCheckListInfo.getWeight10())
                .weight15(customHeaderCheckListInfo.getWeight15())
                .weight20(customHeaderCheckListInfo.getWeight20())
                .weight20Over(customHeaderCheckListInfo.getWeight20Over())
                .subBoxCnt(customHeaderCheckListInfo.getSubBoxCnt())
                .mixInCnt(customHeaderCheckListInfo.getMixInCnt())
                .mixInYn(customHeaderCheckListInfo.getMixInCnt().intValue() > 0 ? "Y" : "N")
                .inCheckEntityList(toInCheckEntity(purchaseOrder.getItemList()))
                .build();
    }


    public InBulkInsertDTO purchaseOrderToInBulkInsertDTOWhenSales(PurchaseOrder purchaseOrder) {
        return InBulkInsertDTO.builder()
                .inDt(DateTimeUtil.localDateTimeToString(purchaseOrder.getReceivedDateTime(), DATE_FORMATTER_yyyyMMdd))
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getInSlipNo())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .supCd(purchaseOrder.getVendorId().getValue())
                .inDiv(OrderType.SALES.getErpCode())
                .inWay(purchaseOrder.getVehicleType().getErpCode())
                .pltCnt(purchaseOrder.getCustomHeaderCheckListInfo().getPltCnt())
                .inOrdSlipNo(purchaseOrder.getId().getValue())
                .inOrdSeq(purchaseOrder.getItemList().get(0).getOrderLineId().getValue())
                .inEntityList(toInEntityWhenSales(purchaseOrder.getItemList()))
                .build();
    }

    public InBulkInsertDTO purchaseOrderToInBulkInsertDTOWhenReturn(PurchaseOrder purchaseOrder){
        return InBulkInsertDTO.builder()
                .inDt(DateTimeUtil.localDateTimeToString(purchaseOrder.getReceivedDateTime(), DATE_FORMATTER_yyyyMMdd))
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getInSlipNo())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .supCd(purchaseOrder.getVendorId().getValue())
                .inDiv(OrderType.RETURN.getErpCode())  //입고구분: 반품(2)
                .inWay(purchaseOrder.getVehicleType().getErpCode())  //입고방식: 택배(01), 차량(02)
                .pltCnt(purchaseOrder.getCustomHeaderCheckListInfo().getPltCnt())
                .inOrdSlipNo(purchaseOrder.getId().getValue())
                .rtnBoxTp(purchaseOrder.getCustomHeaderCheckListInfo().getReturnBoxSize())
                .inEntityList(toInEntityWhenReturn(purchaseOrder.getItemList()))
                .build();
    }

    private List<InCheckEntity> toInCheckEntity(List<Item> itemList) {
        return itemList.stream().map(item ->
                        InCheckEntity.builder()
                                .ordSeq(item.getOrderLineId().getValue())
                                .lotNo(item.getLotNo())
                                .godCd(item.getId().getValue())
                                .inQty(item.getQuantity())
                                .godBarcdAttCnt(item.getBarcodeQuantity())
                                .build()
                )
                .toList();
    }

    private List<InEntity> toInEntityWhenSales(List<Item> itemList) {
        return itemList.stream().map(item ->
                        InEntity.builder()
                                .ordSeq(item.getOrderLineId().getValue())
                                .lotNo(item.getLotNo())
                                .godCd(item.getId().getValue())
                                .inQty(item.getQuantity())
                                .build()
                )
                .toList();
    }

    private List<InEntity> toInEntityWhenReturn(List<Item> itemList) {
        return itemList.stream().map(item ->
                        InEntity.builder()
                                .ordSeq(item.getOrderLineId().getValue())
                                .lotNo(item.getLotNo())
                                .godCd(item.getId().getValue())
                                .inQty(item.getQuantity())
                                .rtnGodCheckStat(ItemCondition.getErpRtnGodStatusCodeBy(item.getCondition()))
                                //.remark(item.getMemo())
                                .cstMemo(item.getMemo())
                                .build()
                )
                .toList();
    }

    public InOrdDTO purchaseOrderToInOrdDTO(PurchaseOrder purchaseOrder) {
        return InOrdDTO.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .build();
    }

    public Optional<InOrder> inOrdEntityToInOrder(InOrdEntity inOrdEntityOrNull) {
        if (inOrdEntityOrNull == null) {
            return Optional.empty();
        }

        return Optional.of(InOrder.builder()
                .vendorId(new VendorId(inOrdEntityOrNull.getSupCd()))
                .orderDate(DateTimeUtil.stringToLocalDate(inOrdEntityOrNull.getOrdDt(), DATE_FORMATTER_yyyyMMdd))
                .releasableDate(!inOrdEntityOrNull.getReleaseDt().equals("0000-00-00 00:00:00") ? DateTimeUtil.stringToLocalDate(inOrdEntityOrNull.getReleaseDt(), DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss) : null)
                .releaseType(inOrdEntityOrNull.getReleaseGbn() != null ? ReleaseType.findByErpCode(inOrdEntityOrNull.getReleaseGbn()) : null)
                .centerArrivalType(inOrdEntityOrNull.getCenArvGbn() != null ? CenterArrivalType.findByErpCode(inOrdEntityOrNull.getCenArvGbn()) : null)
                .centerArrivalDate(inOrdEntityOrNull.getCenArvDt())
                .centerArrivalTime(inOrdEntityOrNull.getCenArvTime())
                .vehicleType(inOrdEntityOrNull.getInWay() == null ? null : VehicleType.findByErpCode(inOrdEntityOrNull.getInWay()))
                .build()
        );
    }

    public ClientConfirmBulkInsertDTO purchaseOrderToClientConfirmBulkInsertDTO(PurchaseOrder purchaseOrder) {
        List<ClientConfirmEntity> clientConfirmEntityList = purchaseOrder.getDamagedItemList().stream().map(item ->
                ClientConfirmEntity.builder()
                        .receiptNo(item.getReceiptNo())
                        .godCd(item.getId().getValue())
                        .clientConfirmMemo(item.getMemo())
                        .clientConfirmImgPath(item.getImagePath())
                        .build()
        ).toList();
        return ClientConfirmBulkInsertDTO.builder()
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .clientConfirmEntityList(clientConfirmEntityList)
                .build();
    }

    public ClientConfirmYnBulkUpdateDTO purchaseOrderToClientConfirmYnDTO(PurchaseOrder purchaseOrder) {
        List<InOrdEntity> inOrdEntityList = purchaseOrder.getDamagedItemList().stream().map(item ->
                InOrdEntity.builder()
                        .godCd(item.getId().getValue())
                        .build()
        ).toList();
        return ClientConfirmYnBulkUpdateDTO.builder()
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .slipNo(purchaseOrder.getId().getValue())
                .inOrdEntityList(inOrdEntityList)
                .build();
    }

    public SlipNoDTO toSlipNoDTO(String warehouseCode, String division) {
        return SlipNoDTO.builder()
                .whCd(warehouseCode)
                .div(division)
                .build();
    }

    public LocNoDTO toLocNoDTO(String warehouseCode, String zone) {
        return LocNoDTO.builder().whCd(warehouseCode)
                .zone(zone)
                .build();
    }

    public List<ItemSimple> godEntityListToItemSimpleList(List<GodEntity> godEntityList) {
        if (godEntityList == null || godEntityList.size() == 0) {
            return new ArrayList<>();
        }

        return godEntityList.stream().map(godEntity ->
                        ItemSimple.Builder.builder()
                                .id(new ItemId(godEntity.getGodCd()))
                                .enabled(EBooleanType.isY(godEntity.getUseYn()))
                                .build()
                )
                .toList();
    }

    public List<Item> clientConfirmEntityToItem(List<ClientConfirmEntity> clientConfirmEntityList) {
        if (clientConfirmEntityList == null || clientConfirmEntityList.size() == 0) {
            return new ArrayList<>();
        }
        return clientConfirmEntityList.stream().map(clientConfirmEntity -> toItem(clientConfirmEntity)).toList();
    }

    private Item toItem(ClientConfirmEntity entity) {
        return Item.Builder.builder()
                .id(new ItemId(entity.getGodCd()))
                .condition(InventoryCondition.UNSERVICEABLE.getSolochainCode())
                .receiptNo(entity.getReceiptNo()) //영수증번호
                .build();
    }

}
