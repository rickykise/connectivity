package ai.fassto.connectivity.domain.workorder.helper.update;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderItemDisabledException;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderItemNotFoundException;
import ai.fassto.connectivity.domain.workorder.application.port.output.repository.WorkOrderRepository;
import ai.fassto.connectivity.domain.workorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrderResponse;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderNotFoundException;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderResponseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkOrderUpdateRequestDefaultHelper {

    private final WorkOrderRepository workOrderRepository;

    @Transactional
    public void persistWhenActive(WorkOrder workOrder){
        commonProcess(workOrder);
    }

    @Transactional
    public void persistWhenCompleted(WorkOrder workOrder){
        commonProcess(workOrder);
    }

    @Transactional
    public void persistWhenCancelled(WorkOrder workOrder){
        commonProcess(workOrder);
    }

    private void activeProcess(WorkOrder workOrder){

    }

    private void completeProcess(WorkOrder workOrder){

    }

    private void cancelledProcess(WorkOrder workOrder){

    }

    private void commonProcess(WorkOrder workOrder){
        //유통가공 작업 신청여부 확인
        checkWorkOrder(workOrder.getId().getValue());

        //상품 존재여부, 사용여부 확인
        checkItems(workOrder.getOrderLineList());

        /* 유통가공 작업 상태 변경 UpdateWrkStat */
        workOrderRepository.updateWorkStatus(workOrder);

        /* 유통가공 작업 상태 이력 저장 insertWorkStatusHistory */
        workOrderRepository.insertWorkStatusHistory(workOrder);

        //작업결과 반영전 데이터 있는지 확인
        checkWorkOrderResponse(workOrder);

        /* 완료 수량, 취소 수량 설정 updateBulkQty */
        workOrderRepository.updateBulkQtyWhenOrderLine(workOrder); //slip_no 와 ItemSeq 기준으로 일괄 업데이트 (tb_work_order_resp)
        workOrderRepository.updateBulkQtyWhenComponent(workOrder);
    }

    //유통가공 작업 신청여부 확인
    void checkWorkOrder(String slipNo){
        boolean hasWorkOrder = workOrderRepository.existsWorkOrderRequest(slipNo);
        if (!hasWorkOrder) {
            throw new WorkOrderNotFoundException(new ErrorDetail("slipNo", slipNo));
        }
    }

    //상품 존재여부, 사용여부 확인
    void checkItems(List<OrderLine> orderLineList) {
        if (orderLineList == null || orderLineList.size() == 0) {
            return;
        }

        List<ItemId> itemsIdList = orderLineList.stream().map(OrderLine::getItemId).toList();
        List<ItemSimple> foundItemList =  workOrderRepository.findItemSimplesBy(itemsIdList);
        Map<ItemId, ItemSimple> foundItemMap = foundItemList.stream().collect(Collectors.toMap(ItemSimple::getId, Function.identity()));

        for (ItemId id : itemsIdList) {
            ItemSimple itemSimple = foundItemMap.get(id);
            if (itemSimple == null) {
                throw new PurchaseOrderItemNotFoundException(new ErrorDetail("itemId", id.getValue()));
            } else if (!itemSimple.getEnabled()) {
                throw new PurchaseOrderItemDisabledException(new ErrorDetail("itemId", id.getValue()));
            }
        }
    }

    public void checkWorkOrderResponse(WorkOrder workOrder){
        List<Integer> itemSeqList = workOrder.getOrderLineList().stream().map(orderLine -> Integer.valueOf(orderLine.getId().getValue())).toList();
        List<WorkOrderResponse> foundWorkOrderRespList = workOrderRepository.findWorkOrderResponseBy(workOrder.getId().getValue());
        Map<Integer, WorkOrderResponse> foundMap = foundWorkOrderRespList.stream().collect(Collectors.toMap(WorkOrderResponse::getItemSeq, Function.identity()));

        for (Integer itemSeq : itemSeqList) {
            WorkOrderResponse workOrderResponse = foundMap.get(itemSeq);
            if (workOrderResponse == null) {
                throw new WorkOrderResponseNotFoundException(new ErrorDetail("itemSequence", Integer.toString(itemSeq)));
            }
        }

    }

}
