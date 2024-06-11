package ai.fassto.connectivity.domain.workorder.application.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.id.*;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.erp.update.UpdateErpWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.Good;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderRequest;
import ai.fassto.connectivity.domain.workorder.application.dto.solochain.SoloChainWorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.workorder.core.entity.Component_;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.domain.workorder.core.valueobject.QuantityInfo;
import ai.fassto.connectivity.domain.workorder.core.valueobject.QuantityInfo_;
import ai.fassto.connectivity.domain.workorder.enums.WorkDirection;
import ai.fassto.connectivity.domain.workorder.enums.WorkOrderStatus;
import ai.fassto.connectivity.domain.workorder.enums.WorkType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.domain.workorder.enums.WorkDirection.isTo;

@Component
public class WorkOrderDataMapper {
    public WorkOrder soloChainWorkOrderRequestToWorkOrder(SoloChainWorkOrderRequest request, ActionType actionType) {
        return WorkOrder.Builder.builder()
                .id(new WorkOrderId(request.getSlipNo()))
                .actionType(actionType)
                .warehouseId(new WarehouseId(request.getWhCd()))
                .requiredDate(request.getRequestYmd())
                .customerId(new CustomerId(request.getCstCd()))
                .type(WorkType.findBy(request.getWrkDiv()))
                .memo(request.getMemo())
                .orderLineMultiValueMap(toOrderLineMultiValueMap(request.getGdsList()))
                .build();
    }

    public WorkOrder updateErpWorkOrderRequestToWorkOrder(UpdateErpWorkOrderRequest request) {
        return WorkOrder.Builder.builder()
                .id(new WorkOrderId(request.slipNo()))
                .workDate(request.workDate())
                .workStatus(WorkOrderStatus.findBy(request.workStatus()))
                .memo(request.memo())
                .type(WorkType.findBy(request.workType()))
                .workerTime(request.workerTime())
                .workerCnt(request.workerCnt())
                .orderLineList(toOrderLineList(request.orderLines()))
                .componentList(toComponentList(request.components()))
                .build();
    }

    public UpdateErpWorkOrderResponse workOrderToErpWorkOrderResponse(WorkOrder workOrder) {
        return new UpdateErpWorkOrderResponse(workOrder.getId().getValue());
    }

    public SoloChainWorkOrderResponse workOrderToSoloChainWorkOrderResponse(WorkOrder workOrder) {
        return new SoloChainWorkOrderResponse(workOrder.getId().getValue());
    }

    private LinkedMultiValueMap<WorkDirection, OrderLine> toOrderLineMultiValueMap(List<Good> goodList) {
        LinkedMultiValueMap<WorkDirection, OrderLine> orderLineMultiValueMap = new LinkedMultiValueMap<>();
        for (Good good : goodList) {
            if (isTo(good.getGdsDiv())) {
                orderLineMultiValueMap.add(
                        WorkDirection.TO,
                        OrderLine.Builder.builder()
                                .id(new OrderLineId(good.getItemSeq()))
                                .itemId(new ItemId(good.getGdsCd()))
                                .quantityInfo(QuantityInfo.builder().orderQuantity(good.getOrdQty()).build())
                                .lotNo(good.getLotNo())
                                .build()
                );
            } else {
                orderLineMultiValueMap.add(
                        WorkDirection.FROM,
                        OrderLine.Builder.builder()
                                .id(new OrderLineId(good.getItemSeq()))
                                .itemId(new ItemId(good.getGdsCd()))
                                .quantityInfo(QuantityInfo.builder().orderQuantity(good.getOrdQty()).build())
                                .lotNo(good.getLotNo())
                                .build()
                );
            }
        }

        return orderLineMultiValueMap;
    }

    private List<OrderLine> toOrderLineList(List<ai.fassto.connectivity.domain.workorder.application.dto.erp.update.OrderLine> orderLines) {
        if (orderLines != null && orderLines.size() > 0) {
            return orderLines.stream().map(this::toOrderLine).toList();
        }

        return new ArrayList<>();
    }

    private OrderLine toOrderLine(ai.fassto.connectivity.domain.workorder.application.dto.erp.update.OrderLine orderLine) {
        return OrderLine.Builder.builder()
                .id(new OrderLineId(Integer.valueOf(orderLine.getSequence())))
                .itemId(new ItemId(orderLine.getCode()))
                .quantityInfo(toQuantityInfo(orderLine))
                .build();
    }

    private QuantityInfo toQuantityInfo(ai.fassto.connectivity.domain.workorder.application.dto.erp.update.OrderLine orderLine) {
        return QuantityInfo.builder()
                .orderQuantity(orderLine.getOrderQty())
                .completedQuantity(orderLine.getCompletedQty())
                .remainingQuantity(orderLine.getRemainingQty())
                .cancelledQuantity(orderLine.getCancelledQty())
                .build();
    }

    private List<Component_> toComponentList(List<ai.fassto.connectivity.domain.workorder.application.dto.erp.update.Component> components) {
        if (components != null && components.size() > 0) {
            return components.stream().map(this::toComponent).toList();
        }
        return new ArrayList<>();
    }

    private Component_ toComponent(ai.fassto.connectivity.domain.workorder.application.dto.erp.update.Component component) {
        return Component_.Builder.builder()
                .id(new OrderLineId(Integer.valueOf(component.getSequence())))
                .itemId(new ItemId(component.getCode()))
                .quantityInfo_(toQuantityInfo(component))
                .build();
    }

    private QuantityInfo_ toQuantityInfo(ai.fassto.connectivity.domain.workorder.application.dto.erp.update.Component component) {
        return QuantityInfo_.builder()
                .orderQuantity(component.getOrderQty())
                .consumedQuantity(component.getConsumedQty())
                .remainingQuantity(component.getRemainingQty())
                .cancelledQuantity(component.getCancelledQty())
                .build();
    }


}
