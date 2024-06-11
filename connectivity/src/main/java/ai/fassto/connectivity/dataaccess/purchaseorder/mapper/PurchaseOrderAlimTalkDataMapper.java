package ai.fassto.connectivity.dataaccess.purchaseorder.mapper;

import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.dataaccess.common.dto.CenArrivalTalkDTO;
import ai.fassto.connectivity.dataaccess.common.dto.ConvertAdviceTalkDTO;
import ai.fassto.connectivity.dataaccess.common.dto.DelayArrivalTalkDTO;
import ai.fassto.connectivity.dataaccess.common.dto.InCompleteTalkDTO;
import ai.fassto.connectivity.dataaccess.common.dto.ClientConfirmTalkDTO;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Item;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import org.springframework.stereotype.Component;

import java.util.List;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyy_MM_dd;
import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss;

@Component
public class PurchaseOrderAlimTalkDataMapper {
    public ClientConfirmTalkDTO purchaseOrderToClientConfirmTalkDTO(PurchaseOrder purchaseOrder) {
        List<Item> damagedItemList = purchaseOrder.getDamagedItemList();
        List<String> godCdList = damagedItemList.stream().map(item -> item.getId().getValue()).distinct().toList();    //상품코드 중복제거
        return ClientConfirmTalkDTO.builder()
                .whCd(purchaseOrder.getWarehouseId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .slipNo(purchaseOrder.getId().getValue())
                .orderType(purchaseOrder.getType().getOrderType())
                .godCdList(godCdList)
                .build();
    }

    public CenArrivalTalkDTO purchaseOrderToCenArrivalTalkDTO(PurchaseOrder purchaseOrder) {
        return CenArrivalTalkDTO.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .ordDt(DateTimeUtil.localDateToString(purchaseOrder.getOrderDate(), DATE_FORMATTER_yyyy_MM_dd))         //yyyyMMdd -> yyyy-MM-dd
                .releaseDt(DateTimeUtil.localDateToString(purchaseOrder.getReleasableDate(), DATE_FORMATTER_yyyy_MM_dd)) //yyyy-MM-dd HH:mm:ss -> yyyy-MM-dd
                .build();
    }

    public ConvertAdviceTalkDTO purchaseOrderToConvertAdviceTalkDTO(PurchaseOrder purchaseOrder) {
        return ConvertAdviceTalkDTO.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .cenArvTime(DateTimeUtil.localDateTimeToString(purchaseOrder.getReceivedDateTime(), DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss))
                .ordDt(DateTimeUtil.localDateToString(purchaseOrder.getOrderDate(), DATE_FORMATTER_yyyy_MM_dd))         //yyyyMMdd -> yyyy-MM-dd
                .releaseDt(DateTimeUtil.localDateToString(purchaseOrder.getReleasableDate(), DATE_FORMATTER_yyyy_MM_dd)) //yyyy-MM-dd HH:mm:ss -> yyyy-MM-dd
                .build();
    }

    public DelayArrivalTalkDTO purchaseOrderToDelayArrivalTalkDTO(PurchaseOrder purchaseOrder) {
        return DelayArrivalTalkDTO.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .warehouseCode(purchaseOrder.getWarehouseId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .releaseType(purchaseOrder.getCenterArrivalInfo().releaseType())
                .cenArvTime(DateTimeUtil.localDateTimeToString(purchaseOrder.getReceivedDateTime(), DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss))
                .ordDt(DateTimeUtil.localDateToString(purchaseOrder.getOrderDate(), DATE_FORMATTER_yyyy_MM_dd))
                .build();
    }


    public InCompleteTalkDTO purchaseOrderToInCompleteTalk(PurchaseOrder purchaseOrder) {
        return InCompleteTalkDTO.builder()
                .slipNo(purchaseOrder.getId().getValue())
                .cstCd(purchaseOrder.getCustomerId().getValue())
                .ordDt(DateTimeUtil.localDateToString(purchaseOrder.getOrderDate(), DATE_FORMATTER_yyyy_MM_dd))         //yyyyMMdd -> yyyy-MM-dd
                .releaseDt(DateTimeUtil.localDateToString(purchaseOrder.getReleasableDate(), DATE_FORMATTER_yyyy_MM_dd)) //yyyy-MM-dd HH:mm:ss -> yyyy-MM-dd
                .build();
    }
}
