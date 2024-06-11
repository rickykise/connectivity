package ai.fassto.connectivity.dataaccess.salesorder.mapper;

import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.dataaccess.common.dto.SlipNoDTO;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import ai.fassto.connectivity.dataaccess.salesorder.dto.*;
import ai.fassto.connectivity.dataaccess.salesorder.entity.*;
import ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType;
import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.salesorder.core.entity.Box;
import ai.fassto.connectivity.domain.salesorder.core.entity.Consumable;
import ai.fassto.connectivity.domain.salesorder.core.entity.Item;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.*;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.BoxCategory;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.CourierType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyyMMdd;

@Component
public class SalesOrderDataAccessMapper {

    public OutOrdDTO salesOrderToOutOrdDTO(SalesOrder salesOrder) {
        return OutOrdDTO.builder()
                .slipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutPickOrdDTO salesOrderToOutPickOrdDTO(SalesOrder salesOrder) {
        return OutPickOrdDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .build();
    }

    public OutInventoryAssignLotNoBulkUpdateDTO salesOrderToOutInventoryAssignLotNoBulkUpdateDTO(SalesOrder salesOrder) {
        List<OutInventoryAssignEntity> outInventoryAssignEntityList = salesOrder.getItems().stream().map(this::toOutInventoryAssignEntity).toList();
        return OutInventoryAssignLotNoBulkUpdateDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .outInventoryAssignEntityList(outInventoryAssignEntityList)
                .build();
    }

    public OutInventoryAssignDeleteDTO salesOrderToOutInventoryAssignDeleteDTO(SalesOrder salesOrder) {
        return OutInventoryAssignDeleteDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .build();
    }

    public OutInventoryAssignBulkInsertDTO salesOrderToOutInventoryAssignBulkInsertDTO(SalesOrder salesOrder) {
        List<OutInventoryAssignEntity> outInventoryAssignEntityList = salesOrder.getItems().stream().map(this::toOutInventoryAssignEntity).toList();
        return OutInventoryAssignBulkInsertDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .outInventoryAssignEntityList(outInventoryAssignEntityList)
                .build();
    }

    private OutInventoryAssignEntity toOutInventoryAssignEntity(Item item){
        return OutInventoryAssignEntity.builder()
                .itemSeq(item.getSequence())
                .lotNo(item.getLot())
                .godCd(item.getCode())
                .assignQty(item.getQty())
                .build();
    }

    public OutPickMapEntity salesOrderToOutPickMapEntity(SalesOrder salesOrder) {
        return OutPickMapEntity.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .allocateYn(salesOrder.getAllocateYn())
                .build();
    }

    public OutOrdSetBulkInsertDTO salesOrderToOutOrdSetBulkInsertDTO(SalesOrder salesOrder) {
        List<OutOrdSetEntity> outOrdSetEntityList = salesOrder.getItems().stream().map(v ->
                OutOrdSetEntity.builder()
                        .godCd(v.getCode())
                        .lotNo(v.getLot())
                        .setQty(v.getQty())
                        .distTermDt(v.getExpiryDate() == null ? "" : DateTimeUtil.localDateToString(v.getExpiryDate(), DATE_FORMATTER_yyyyMMdd))
                        .build()
        ).toList();

        return OutOrdSetBulkInsertDTO.builder()
                .ordDt(salesOrder.getOrderDate().format(DATE_FORMATTER_yyyyMMdd))
                .whCd(salesOrder.getWarehouseId().getValue())
                .slipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .shopCd(salesOrder.getShop().getId().getValue())
                .ordNo(salesOrder.getOrderNo())
                .ordSeq(salesOrder.getOrderSequence())
                .outOrdSetEntityList(outOrdSetEntityList)
                .build();
    }

    public OutOrdEntity salesOrderToOutOrdEntity(SalesOrder salesOrder) {
        return OutOrdEntity.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .slipNo(salesOrder.getId().getValue())
                .wrkStat(salesOrder.getWorkStatus().getErpCode())
                .build();
    }

    public OutPickOrdPickSeqDTO salesOrderToPickSeqDTO(SalesOrder salesOrder) {
        return OutPickOrdPickSeqDTO.builder()
                .pickDt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .whCd(salesOrder.getWarehouseId().getValue())
                .build();
    }

    public OutPickOrdBulkInsertDTO salesOrderToOutPickOrdBulkInsertDTO(SalesOrder salesOrder, int pickSeq) {
        List<OutPickOrdEntity> outPickOrdEntityList = salesOrder.getItems().stream().map(v ->
                OutPickOrdEntity.builder()
                        .locNo(v.getLoc())
                        .luNo(v.getLu())
                        .lotNo(v.getLot())
                        .godCd(v.getCode())
                        .setQty(v.getQty())
                        .pickQty(v.getQty())
                        .build()
        ).toList();

        return OutPickOrdBulkInsertDTO.builder()
                .pickDt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .pickSeq(pickSeq)
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .shopCd(salesOrder.getShop().getId().getValue())
                .ordNo(salesOrder.getOrderNo())
                .ordSeq(salesOrder.getOrderSequence())
                .wrkStat(salesOrder.getPickOrderWorkStatus().getErpCode())
                .outPickOrdEntityList(outPickOrdEntityList)
                .hasLotNo(salesOrder.isHasLotNo())
                .build();
    }

    public OutPickOrdPickQtyBulkUpdateDTO salesOrderToOutPickOrdPickQtyBulkUpdateDTO(SalesOrder salesOrder) {
        List<OutPickOrdEntity> outPickOrdEntityList = salesOrder.getItems().stream().map(v ->
                OutPickOrdEntity.builder()
                        .pickQty(v.getQty())
                        .lotNo(v.getLot())
                        .godCd(v.getCode())
                        .pickTime(salesOrder.getWorkDate())
                        .build()
        ).toList();

        return OutPickOrdPickQtyBulkUpdateDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .outPickOrdEntityList(outPickOrdEntityList)
                .build();
    }

    public OutOrdSetExistDTO salesOrderToOutOrdSetExistDTO(SalesOrder salesOrder) {
        return OutOrdSetExistDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .slipNo(salesOrder.getId().getValue())
                .build();
    }

    public OutPickOrdExistDTO salesOrderToOutPickOrdExistDTO(SalesOrder salesOrder) {
        return OutPickOrdExistDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .build();
    }

    public OutPackEntity salesOrderToOutPackEntity(SalesOrder salesOrder) {
        return OutPackEntity.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutPackExistDTO salesOrderToOutPackExistDTO(SalesOrder salesOrder) {
        return OutPackExistDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .build();
    }

    public OutInvoiceExistDTO salesOrderToOutInvoiceExistDTO(SalesOrder salesOrder) {
        return OutInvoiceExistDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutExistDTO salesOrderToOutExistDTO(SalesOrder salesOrder) {
        return OutExistDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .build();
    }

    public OutPackWhSubExistDTO salesOrderToOutPackWhSubExistDTO(SalesOrder salesOrder) {
        return OutPackWhSubExistDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .build();
    }

    public OutPickOrdPackQtyBulkUpdateDTO salesOrderToOutPickOrdPackQtyBulkUpdateDTO(SalesOrder salesOrder) {
        List<OutPickOrdEntity> outPickOrdEntityList = salesOrder.getItems().stream().map(v ->
                OutPickOrdEntity.builder()
                        .packQty(v.getQty())
                        .lotNo(v.getLot())
                        .godCd(v.getCode())
                        .packTime(salesOrder.getWorkDate())
                        .build()
        ).toList();

        return OutPickOrdPackQtyBulkUpdateDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .outPickOrdEntityList(outPickOrdEntityList)
                .build();
    }

    public OutPickOrdEntity salesOrderToOutPickOrdEntity(SalesOrder salesOrder) {
        return OutPickOrdEntity.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .wrkStat(salesOrder.getPickOrderWorkStatus().getErpCode())
                .build();
    }

    public OutPackBulkInsertDTO salesOrderToOutPackBulkInsertDTO(SalesOrder salesOrder) {
        List<Box> boxes = salesOrder.getBoxes();
        List<OutPackEntity> outPackEntityList = salesOrder.getItems().stream().map(v -> {
                    Integer boxSeq = v.getBoxSeq();
                    Box box = boxes.stream()
                            .filter(x -> x.getBoxSeq().equals(boxSeq))
                            .findFirst().get();

                    return OutPackEntity.builder()
                            .packSeq(v.getBoxSeq())
                            .godCd(v.getCode())
                            .packQty(v.getQty())
                            .boxNo(box.getBoxId())
                            .boxDiv(BoxCategory.findBySolochainCode(box.getCategory()).getErpCode())
                            .boxTp(box.getBoxType())
                            .bscFare(box.getBasicFare())
                            .airFare(box.getAirFare())
                            .dealFare(box.getDealFare())
                            .shipFare(box.getShipFare())
                            .parcelCd(StringUtils.isBlank(box.getCourierCode()) /*|| !CourierType.containsBy(box.getCourierCode()) */ ? null : CourierType.getErpCodeBy(box.getCourierCode()))
                            .invoiceNo(StringUtils.isBlank(box.getInvoiceNo()) ? null : box.getInvoiceNo())
                            .boxQuantity(v.getBoxSeq())
                            .realPackingVideo(box.getRealPackingVideo())
                            .build();
                }
        ).toList();

        // 긴급출고 건 처리 240306
        String outPackWorkTp = Integer.parseInt(salesOrder.getOutOrdEntity().getOrdDt()) >= Integer.parseInt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd)) && "Y".equals(salesOrder.getOutOrdEntity().getEmgrYn()) ? "3" : "1";
        return OutPackBulkInsertDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .packDt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .outOrdSlipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .shopCd(salesOrder.getShop().getId().getValue())
                .ordNo(salesOrder.getOrderNo())
                .ordSeq(salesOrder.getOrderSequence())
                .workTp(outPackWorkTp)
                .outPackEntityList(outPackEntityList)
                .build();
    }

    public OutBulkInsertDTO salesOrderToOutBulkInsertDTO(SalesOrder salesOrder) {
        List<OutEntity> outEntityList = salesOrder.getItems().stream().map(v ->
                OutEntity.builder()
                        .locNo(v.getLoc())
                        .luNo(v.getLu())
                        .lotNo(v.getLot())
                        .godCd(v.getCode())
                        .outQty(v.getQty())
                        .build()
        ).toList();

        return OutBulkInsertDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outDt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .slipNo(salesOrder.getOutSlipNo())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .shopCd(salesOrder.getShop().getId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .ordNo(salesOrder.getOrderNo())
                .ordSeq(salesOrder.getOrderSequence())
                .outType(salesOrder.getOutType())
                .outEntityList(outEntityList)
                .build();
    }

    public OutInvoiceBulkInsertDTO salesOrderToOutInvoiceBulkInsertDTO(SalesOrder salesOrder) {
        List<Box> boxes = salesOrder.getBoxes();
        List<OutInvoiceEntity> outInvoiceEntityList = salesOrder.getSeperatedItems().stream().map(v -> {
                    Integer boxSeq = v.getBoxSeq();
                    Box box = boxes.stream()
                            .filter(x -> x.getBoxSeq().equals(boxSeq))
                            .findFirst().get();

                    return OutInvoiceEntity.builder()
                            .godCd(v.getCode())
                            .outQty(v.getQty())
                            .godDiv(v.getGodDiv())
                            .invoiceNo(StringUtils.isBlank(box.getInvoiceNo()) ? null : box.getInvoiceNo())
                            .build();
                }

        ).toList();

        return OutInvoiceBulkInsertDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outDt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .slipNo(salesOrder.getOutSlipNo())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .shopCd(salesOrder.getShop().getId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .outInvoiceEntityList(outInvoiceEntityList)
                .build();
    }

    public OutOrdParcelCdInvoiceNoDTO salesOrderToOutOrdParcelCdInvoiceNoUpdateDTO(SalesOrder salesOrder) {
        Box firstBox = salesOrder.getBoxes().get(0);
        return OutOrdParcelCdInvoiceNoDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .parcelCd(StringUtils.isBlank(firstBox.getCourierCode()) ? null : CourierType.getErpCodeBy(firstBox.getCourierCode()))
                .invoiceNo(StringUtils.isBlank(firstBox.getInvoiceNo()) ? null : firstBox.getInvoiceNo())
                .build();
    }

    public OutOrdParcelCdInvoiceNoToNullDTO salesOrderToOutOrdParcelCdInvoiceNoToNullDTO(SalesOrder salesOrder) {
        return OutOrdParcelCdInvoiceNoToNullDTO.builder()
                .outOrdSlipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .build();
    }

    public OutPickMapStatusDTO salesOrderToOutPickMapStatusDTO(SalesOrder salesOrder) {
        return OutPickMapStatusDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .allocateYn(salesOrder.getAllocateYn())
                .build();
    }

    public OutOrdSetDeleteDTO salesOrderToOutOrdSetDeleteDTO(SalesOrder salesOrder) {
        return OutOrdSetDeleteDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .slipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutPickOrdDeleteDTO salesOrderToOutPickOrdDeleteDTO(SalesOrder salesOrder) {
        return OutPickOrdDeleteDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutPackDeleteDTO salesOrderToOutPackDeleteDTO(SalesOrder salesOrder) {
        return OutPackDeleteDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutDeleteDTO salesOrderToOutDeleteDTO(SalesOrder salesOrder) {
        return OutDeleteDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }

    public OutPackWhSubDeleteDTO salesOrderToOutPackWhSubDeleteDTO(SalesOrder salesOrder) {
        return OutPackWhSubDeleteDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .build();
    }

    public OutPackParcelCdInvoiceNoDTO salesOrderToOutPackParcelCdInvoiceNoBulkUpdateDTO(SalesOrder salesOrder) {
        List<Box> boxes = salesOrder.getBoxes();
        List<OutPackEntity> outPackEntityList = salesOrder.getItems().stream().map(v -> {
                    Integer boxSeq = v.getBoxSeq();
                    Box box = boxes.stream()
                            .filter(x -> x.getBoxSeq().equals(boxSeq))
                            .findFirst().get();

                    return OutPackEntity.builder()
                            .packSeq(v.getBoxSeq())
                            .godCd(v.getCode())
                            .parcelCd(StringUtils.isBlank(box.getCourierCode()) ? null : CourierType.getErpCodeBy(box.getCourierCode()))
                            .invoiceNo(StringUtils.isBlank(box.getInvoiceNo()) ? null : box.getInvoiceNo())
                            .build();
                }
        ).toList();

        return OutPackParcelCdInvoiceNoDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .outPackEntityList(outPackEntityList)
                .build();
    }

    public OutOrdOrdDivDTO salesOrderToOutOrdOrdDivDTO(SalesOrder salesOrder, String ordDiv) {
        return OutOrdOrdDivDTO.builder()
                .slipNo(salesOrder.getId().getValue())
                .whCd(salesOrder.getWarehouseId().getValue())
                .ordDiv(ordDiv)
                .build();
    }

    public SlipNoDTO toSlipNoDTO(SalesOrder salesOrder, String division) {
        return SlipNoDTO.builder()       //출고 slipNo 생성
                .whCd(salesOrder.getWarehouseId().getValue())
                .div(division)
                .dt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .build();
    }

    public Optional<OutOrder> outOrdEntityToOutOrder(OutOrdEntity outOrdEntityOrNull){
        if (outOrdEntityOrNull == null) {
            return Optional.empty();
        }

        return Optional.of(OutOrder.builder()
                .customerId(new CustomerId(outOrdEntityOrNull.getCstCd()))
                .warehouseId(new WarehouseId(outOrdEntityOrNull.getWhCd()))
                .shopId(new ShopId(outOrdEntityOrNull.getShopCd()))
                .outDiv(outOrdEntityOrNull.getOutDiv())
                .parcelCd(outOrdEntityOrNull.getParcelCd())
                .invoiceNo(outOrdEntityOrNull.getInvoiceNo())
                .outType(Integer.valueOf(outOrdEntityOrNull.getOutTp()))
                .orderNo(outOrdEntityOrNull.getOrdNo())
                .orderSequence(outOrdEntityOrNull.getOrdSeq())
                .orderDate(DateTimeUtil.stringToLocalDate(outOrdEntityOrNull.getOrdDt(), DATE_FORMATTER_yyyyMMdd))
                .workStatus(outOrdEntityOrNull.getWrkStat())
                .outWay(outOrdEntityOrNull.getOutWay())
                .build()
        );
    }

    public Optional<OutPickOrder> outOrdEntityToOutPickOrder(OutPickOrdEntity outPickOrdEntityOrNull) {
        if (outPickOrdEntityOrNull == null) {
            return Optional.empty();
        }

        return Optional.of(OutPickOrder.builder()
                .workStatus(outPickOrdEntityOrNull.getWrkStat())
                .build()
        );
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
                ).toList();
    }

    public List<OutInventoryAssign> outInventoryAssignEntityListToOutInventoryAssignList(List<OutInventoryAssignEntity> outInventoryAssignEntityList) {
        if (outInventoryAssignEntityList == null || outInventoryAssignEntityList.size() == 0) {
            return new ArrayList<>();
        }

        return outInventoryAssignEntityList.stream().map(outInventoryAssignEntity ->
                        OutInventoryAssign.builder()
                                .salesOrderId(new SalesOrderId(outInventoryAssignEntity.getOutOrdSlipNo()))
                                .itemSeq(outInventoryAssignEntity.getItemSeq())
                                .warehouseId(new WarehouseId(outInventoryAssignEntity.getWhCd()))
                                .lotNo(outInventoryAssignEntity.getLotNo())
                                .godCd(outInventoryAssignEntity.getGodCd())
                                .assignQty(outInventoryAssignEntity.getAssignQty())
                                .build()
                ).toList();
    }

    /*
    public WhSubsidiaryDTO consumableToWareHouseConsumableDTO(Consumable consumable) {
        return WhSubsidiaryDTO.builder()
                .subNo(consumable.getCode())
                .whCd(consumable.getWareHouseCode())
                .subDiv(consumable.getType())
                .subSizeCd(consumable.getSize())
                .build();
    }*/

    public WhSubsidiaryDTO salesOrderToWareHouseConsumableDTO(SalesOrder salesOrder) {
        List<WhSubsidiaryEntity> whSubsidiaryEntityList = salesOrder.getConsumables().stream().map(this::toWhSubsidiaryEntity).toList();
        return WhSubsidiaryDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .whSubsidiaryEntityList(whSubsidiaryEntityList)
                .build();
    }

    public List<Consumable> whSubsidiaryEntityListToWareHouseConsumableList(List<WhSubsidiaryEntity> whSubsidiaryEntityList) {
        if (whSubsidiaryEntityList == null || whSubsidiaryEntityList.size() == 0) {
            return new ArrayList<>();
        }

        return whSubsidiaryEntityList.stream().map( whSubsidiaryEntity ->
                Consumable.Builder.builder()
                        .id(new ConsumableId(whSubsidiaryEntity.getWhIdx()))
                        .code(whSubsidiaryEntity.getSubNo())
                        .size(whSubsidiaryEntity.getSubSizeCd())
                        .type(whSubsidiaryEntity.getSubDiv())
                        .useYn(whSubsidiaryEntity.getSubUseYn())
                        .build()
        ).toList();
    }

    public OutPackWhSubBulkInsertDTO salesOrderToOutPackWhSubBulkInsertDTO(SalesOrder salesOrder) {
        List<OutPackWhSubEntity> outPackWhSubEntityList = salesOrder.getConsumables().stream().map(this::toOutPackWhSubEntity).toList();
        return OutPackWhSubBulkInsertDTO.builder()
                .packDt(DateTimeUtil.localDateTimeToString(salesOrder.getWorkDate(), DATE_FORMATTER_yyyyMMdd))
                .whCd(salesOrder.getWarehouseId().getValue())
                .packSeq(salesOrder.getBoxes().get(0).getBoxSeq())
                .outOrdSlipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .outPackWhSubEntityList(outPackWhSubEntityList)
                .build();
    }

    private OutPackWhSubEntity toOutPackWhSubEntity(Consumable outPackWareHouseConsumable) {
        return OutPackWhSubEntity.builder()
                .whIdx(outPackWareHouseConsumable.getId().getValue())
                .packQty(outPackWareHouseConsumable.getQty())
                .build();
    }

    private WhSubsidiaryEntity toWhSubsidiaryEntity(Consumable consumable) {
        return WhSubsidiaryEntity.builder()
                .subNo(consumable.getCode())
                .build();
    }

    public OutOrdSeperatedGodQtyDTO findSeperatedGodQtyDTO(SalesOrder salesOrder) {
        return OutOrdSeperatedGodQtyDTO.builder()
                .whCd(salesOrder.getWarehouseId().getValue())
                .slipNo(salesOrder.getId().getValue())
                .cstCd(salesOrder.getCustomer().getId().getValue())
                .build();
    }
}
