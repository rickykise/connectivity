package ai.fassto.connectivity.dataaccess.workorder.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.WorkOrderResponseId;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import ai.fassto.connectivity.dataaccess.workorder.dto.WorkOrderRequestGdsBulkUpdateDTO;
import ai.fassto.connectivity.dataaccess.workorder.dto.WorkOrderResponseBulkUpdateDTO;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderRequestGdsEntity;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderRequestMstEntity;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderResponseEntity;
import ai.fassto.connectivity.dataaccess.workorder.entity.WorkOrderWrkStatHistoryEntity;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.workorder.core.entity.Component_;
import ai.fassto.connectivity.domain.workorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrderResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkOrderDataAccessMapper {

    public WorkOrderRequestMstEntity workOrderToWorkOrderRequestMstEntity(WorkOrder workOrder){
        return WorkOrderRequestMstEntity.builder()
                .slipNo(workOrder.getId().getValue())
                .wrkStat(workOrder.getWorkOrderStatus().getErpCode())
                .updId("CONNECTIVITY")
                .build();
    }

    public WorkOrderResponseBulkUpdateDTO workOrderToWorkOrderResponseBulkUpdateDTOWhenOrderLine(WorkOrder workOrder){
        List<WorkOrderResponseEntity> workOrderResponseEntityList = workOrder.getOrderLineList().stream().map(this::toWorkOrderResponseEntityWhenOrderLine).toList();
        return WorkOrderResponseBulkUpdateDTO.builder()
                .slipNo(workOrder.getId().getValue())
                .workDate(workOrder.getWorkDate())
                .workerTime(StringUtils.isBlank(workOrder.getWorkerTime()) ? null : Integer.valueOf(workOrder.getWorkerTime()))
                .workerCnt(StringUtils.isBlank(workOrder.getWorkerCnt()) ? null : Integer.valueOf(workOrder.getWorkerCnt()))
                .workOrderResponseEntityList(workOrderResponseEntityList)
                .updId("CONNECTIVITY")
                .build();
    }


    public WorkOrderResponseBulkUpdateDTO workOrderToWorkOrderResponseBulkUpdateDTOWhenComponent(WorkOrder workOrder){
        List<WorkOrderResponseEntity> workOrderResponseEntityList = workOrder.getComponentList().stream().map(this::toWorkOrderResponseEntityWhenComponent).toList();
        return WorkOrderResponseBulkUpdateDTO.builder()
                .slipNo(workOrder.getId().getValue())
                .workDate(workOrder.getWorkDate())
                .workerTime(StringUtils.isBlank(workOrder.getWorkerTime()) ? null : Integer.valueOf(workOrder.getWorkerTime()))
                .workerCnt(StringUtils.isBlank(workOrder.getWorkerCnt()) ? null : Integer.valueOf(workOrder.getWorkerCnt()))
                .workOrderResponseEntityList(workOrderResponseEntityList)
                .updId("CONNECTIVITY")
                .build();
    }

    private WorkOrderResponseEntity toWorkOrderResponseEntityWhenOrderLine(OrderLine orderLine){
        return  WorkOrderResponseEntity.builder()
                .itemSeq(orderLine.getId().getValue())
                .completedQty(orderLine.getQuantityInfo().completedQuantity())
                .cancelledQty(orderLine.getQuantityInfo().cancelledQuantity())
                .updId("CONNECTIVITY")
                .build();
    }

    private WorkOrderResponseEntity toWorkOrderResponseEntityWhenComponent(Component_ component){
        return  WorkOrderResponseEntity.builder()
                .itemSeq(component.getId().getValue())
                .completedQty(component.getQuantityInfo_().consumedQuantity())
                .cancelledQty(component.getQuantityInfo_().cancelledQuantity())
                .updId("CONNECTIVITY")
                .build();
    }


    public WorkOrderWrkStatHistoryEntity workOrderToWorkOrderWrkStatHistoryEntity(WorkOrder workOrder){
        return WorkOrderWrkStatHistoryEntity.builder()
                .slipNo(workOrder.getId().getValue())
                .wrkStat(workOrder.getWorkOrderStatus().getErpCode())
                .wrkStatRemark(workOrder.getMemo())
                .regId("CONNECTIVITY")
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

    public List<WorkOrderResponse> workOrderResponseEntityToWorkOrderResponseList(List<WorkOrderResponseEntity> workOrderResponseEntityList){
        if (CollectionUtils.isEmpty(workOrderResponseEntityList)) {
            return new ArrayList<>();
        }

        return workOrderResponseEntityList.stream().map(workOrderResponseEntity ->
                        WorkOrderResponse.Builder.builder()
                                .id(new WorkOrderResponseId(workOrderResponseEntity.getSlipNo()))
                                .itemSeq(workOrderResponseEntity.getItemSeq())
                                .build()
        ).toList();
    }

}
