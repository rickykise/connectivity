package ai.fassto.connectivity.domain.salesorder.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.solochain.SoloChainSalesOrdersResponse;
import ai.fassto.connectivity.domain.salesorder.application.helper.update.SalesOrderUpdateRequestHelper;
import ai.fassto.connectivity.domain.salesorder.application.mapper.ErpSalesOrderDataMapper;
import ai.fassto.connectivity.domain.salesorder.application.mapper.SolochainSalesOrderDataMapper;
import ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.wms.SalesOrderWms;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.exception.SalesOrderNotSupportException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class SalesOrderUpdateRequestHandler {
    private final SolochainSalesOrderDataMapper solochainSalesOrderDataMapper;
    private final ErpSalesOrderDataMapper erpSalesOrderDataMappers;
    private final SalesOrderWms salesOrderWms;
    private final List<SalesOrderUpdateRequestHelper> salesOrderUpdateRequestHelperList;

    public SoloChainSalesOrderResponse updateSalesOrder(SoloChainSalesOrderRequest request) {
        return solochainSalesOrderDataMapper.salesOrderToSoloChainSalesOrderResponse(
                salesOrderWms.update(
                        solochainSalesOrderDataMapper.soloChainSalesOrderRequestToSalesOrder(request, ActionType.UPDATE)
                )
        );
    }

    public SoloChainSalesOrdersResponse updateSalesOrders(SoloChainSalesOrdersRequest request) {
        List<SalesOrder> salesOrders = solochainSalesOrderDataMapper.soloChainSalesOrdersRequestToSalesOrderList(request, ActionType.UPDATE);
        salesOrderWms.updateAsAsync(salesOrders);

        return solochainSalesOrderDataMapper.salesOrderToSoloChainSalesOrdersResponse(solochainSalesOrderDataMapper.salesOrdersToResult(salesOrders));
    }

    /** (2023.04.19 프로세스 수정 by NewFMS 김동욱)
     * 1.출고지시확정(allocate)
      - TB_OUT_PICK_MAP insert, allocateYn -> 'Y'
      - TB_OUT_ORD_SET bulk insert
      - TB_OUT_PICK_ORD bulk insert
      - TB_OUT_ORD update 출고요청(1) -> 출고작업중(2)
      - TB_OUT_INVENTORY_ASSIGN delete and bulk insert (lotNo 로트번호 등록)
      - TMS preCall 호출 -> TB_OUT_ORD 가송장 update 택배사 코드(parcelCd), 송장번호(invoiceNo)
     * 2.피킹(completePicking)
      - TB_OUT_PICK_ORD pickQty update
      - TB_OUT_PICK_ORD wrk_stat 피킹작업중(1) -> 피킹완료(2)
     * 3.패킹완료(completePacking)
      - TB_OUT_PICK_ORD bulk update (피킹수량, 패킹수량)
      - TB_OUT_PICK_ORD update 피킹완료(2) -> 출고완료(4) (2023.02.17 추가)
      - TB_OUT_PACK delete
      - TB_OUT_PACK bulk insert
      - TB_OUT_ORD update 택배사 코드(parcelCd), 송장번호(invoiceNo)
     * 4.출고완료(completeShipping)
      - TB_OUT_PACK delete
      - TB_OUT_PACK bulk insert
      - TB_OUT bulk insert
      - TB_OUT_ORD update 출고작업중(2) -> 출고완료(3)
     * 5.출고지시확정 취소(cancelAllocate)
      - TB_OUT_PICK_MAP update allocateYn -> 'N'
      - TB_OUT_ORD_SET delete
      - TB_OUT_PICK_ORD delete
     **/
    public UpdateErpSalesOrderResponse allocate(UpdateErpSalesOrderRequest request) {
        SalesOrder salesOrder = erpSalesOrderDataMappers.erpSalesOrderRequestToSalesOrder(request);
        SalesOrderUpdateRequestHelper helper = findHelper(salesOrder.getType().getOrderType());

        helper.persistWhenAllocated(salesOrder);

        return erpSalesOrderDataMappers.salesOrderToUpdateErpSalesOrderResponse(salesOrder);
    }

    public UpdateErpSalesOrderResponse completePicking(UpdateErpSalesOrderRequest request) {
        SalesOrder salesOrder = erpSalesOrderDataMappers.erpSalesOrderRequestToSalesOrder(request);
        SalesOrderUpdateRequestHelper helper = findHelper(salesOrder.getType().getOrderType());

        helper.persistWhenPickingCompleted(salesOrder);

        return erpSalesOrderDataMappers.salesOrderToUpdateErpSalesOrderResponse(salesOrder);
    }

    public UpdateErpSalesOrderResponse completePacking(UpdateErpSalesOrderRequest request) {
        SalesOrder salesOrder = erpSalesOrderDataMappers.erpSalesOrderRequestToSalesOrder(request);
        SalesOrderUpdateRequestHelper helper = findHelper(salesOrder.getType().getOrderType());

        helper.persistWhenPackingCompleted(salesOrder);

        return erpSalesOrderDataMappers.salesOrderToUpdateErpSalesOrderResponse(salesOrder);
    }

    public UpdateErpSalesOrderResponse completeShipping(UpdateErpSalesOrderRequest request) {
        SalesOrder salesOrder = erpSalesOrderDataMappers.erpSalesOrderRequestToSalesOrder(request);
        SalesOrderUpdateRequestHelper helper = findHelper(salesOrder.getType().getOrderType());

        helper.persistWhenShippingCompleted(salesOrder);

        return erpSalesOrderDataMappers.salesOrderToUpdateErpSalesOrderResponse(salesOrder);
    }

    public UpdateErpSalesOrderResponse cancelAllocate(UpdateErpSalesOrderRequest request) {
        SalesOrder salesOrder = erpSalesOrderDataMappers.erpSalesOrderRequestToSalesOrder(request);
        SalesOrderUpdateRequestHelper helper = findHelper(salesOrder.getType().getOrderType());

        helper.persistWhenCancelAllocated(salesOrder);

        return erpSalesOrderDataMappers.salesOrderToUpdateErpSalesOrderResponse(salesOrder);
    }

    private SalesOrderUpdateRequestHelper findHelper(OrderType orderType) {
        for (SalesOrderUpdateRequestHelper helper : salesOrderUpdateRequestHelperList) {
            if (helper.is(orderType)) {
                return helper;
            }
        }
        throw new SalesOrderNotSupportException(new ErrorDetail("OrderType", orderType.name()));
    }

}
