package ai.fassto.connectivity.domain.item.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.VendorId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeRequest;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeResponse;
import ai.fassto.connectivity.domain.item.application.dto.solochain.RequestItem;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemRequest;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemResponse;
import ai.fassto.connectivity.domain.item.application.valueobject.ResponseItem;
import ai.fassto.connectivity.domain.item.core.entity.Item;
import ai.fassto.connectivity.domain.item.core.valueobject.DistributionTerm;
import ai.fassto.connectivity.domain.item.core.valueobject.ItemCategory;
import ai.fassto.connectivity.domain.item.core.valueobject.ItemVolume;
import ai.fassto.connectivity.domain.item.core.valueobject.TemperatureManager;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType.isY;

@Component
public class ItemDataMapper {
    public List<Item> soloChainItemRequestToItem(SoloChainItemRequest request, ActionType actionType) {
        return request.getItems().stream().map(requestItem -> toItem(requestItem, actionType)).toList();
    }

    public SoloChainItemResponse itemToSoloChainItemResponse(List<Result> itemList) {
        return new SoloChainItemResponse(itemList.stream().map(this::toResponseItem).toList());
    }

    public List<Result> itemsToSoloChainItemResponse(List<Item> itemList) {
        return itemList.stream().map(this::toResponseItem).toList();
    }

    private Result toResponseItem(Item item) {
        return new Result("success", item.getId().getValue(), item.getName());
    }

    private ResponseItem toResponseItem(Result result) {
        return new ResponseItem(result.godCd(), result.godNm(), result.result());
    }

    private Item toItem(RequestItem item, ActionType actionType) {
        return Item.Builder.builder()
                .id(new ItemId(item.getGodCd()))
                .name(item.getGodNm())
                .barcode(item.getGodBarcd())
                .actionType(actionType)
                .customerId(new CustomerId(item.getCstCd()))
                .vendorId(StringUtils.isBlank(item.getSupCd()) ? null : new VendorId(item.getSupCd()))
                .itemCategory(new ItemCategory(item.getCategoryCode1(), item.getCategoryName1(), item.getCategoryCode2(), item.getCategoryName2()))
                .distributionTerm(DistributionTerm.builder()
                        .managed(isY(item.getDistTermMgtYn()))
                        .daysBeforeOutProhibited(item.getOutStopDay() == null ? 0 : Integer.parseInt(item.getOutStopDay()))
                        .build()
                )
                .makeDateManaged(StringUtils.isBlank(item.getMakeDtMgtYn()) ? null : EBooleanType.isY(item.getMakeDtMgtYn()))
                .completedBox(item.getCompletedBox())
                .temperatureManager(TemperatureManager.builder()
                        .code(item.getDealTemp())
                        .name(item.getDealTempName())
                        .minimumTemperature(item.getMinStrTemp())
                        .maximumTemperature(item.getMaxStrTemp())
                        .build()
                )
                .cushioningMaterialOption(item.getBufGodYn())
                .cushioningMaterialOptionName(item.getBufGodName())
                .materialClassification(item.getGiftDiv())
                .materialClassificationName(item.getGiftDivName())
                .subsidiaryMaterialsType(item.getSubMate())
                .subsidiaryMaterialsTypeName(item.getSubMateName())
                .warehouseId(new WarehouseId(item.getWhCd()))
                .build();
    }

    public ItemVolume erpItemVolumeRequestToItemVolume(ErpItemVolumeRequest request) {
        return ItemVolume.builder()
                .itemCode(request.getItemCode())
                .width(request.getWidth())
                .height(request.getHeight())
                .depth(request.getDepth())
                .dimUnitCode(request.getDimUnitCode())
                .weight(request.getWeight().intValue())
                .weightUnitCode(request.getWeightUnitCode())
                .itemUnitCode(request.getItemUnitCode())
                .build();
    }

    public ErpItemVolumeResponse itemVolumeToErpItemVolumeResponse(ItemVolume itemVolume) {
        return new ErpItemVolumeResponse(itemVolume.getItemCode());
    }
}
