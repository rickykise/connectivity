package ai.fassto.connectivity.domain.purchaseorder.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.erp.update.UpdateErpPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderRequest;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.helper.update.PurchaseOrderUpdateRequestHelper;
import ai.fassto.connectivity.domain.purchaseorder.application.mapper.ErpPurchaseOrderDataMapper;
import ai.fassto.connectivity.domain.purchaseorder.application.mapper.SolochainPurchaseOrderDataMapper;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.external.api.wms.PurchaseOrderWms;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderNotSupportException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseOrderUpdateRequestHandler {
    private final SolochainPurchaseOrderDataMapper solochainPurchaseOrderDataMapper;
    private final ErpPurchaseOrderDataMapper erpPurchaseOrderDataMapper;
    private final PurchaseOrderWms purchaseOrderWms;
    private final List<PurchaseOrderUpdateRequestHelper> purchaseOrderUpdateRequestHelperList;

    public SoloChainPurchaseOrderResponse updatePurchaseOrder(SoloChainPurchaseOrderRequest request) {
        return solochainPurchaseOrderDataMapper.purchaseOrderToSoloChainPurchaseOrderResponse(
                purchaseOrderWms.update(
                        solochainPurchaseOrderDataMapper.soloChainPurchaseOrderRequestToPurchaseOrder(request, ActionType.UPDATE)
                )
        );
    }

    /**
     * 센터도착: ARRIVED
     * 입고검수중: ACTIVE
     * TB_IN_ORD(작업상태 입고검수중으로 변경) update wrk_stat = '2'
     * Damaged 상품인경우:
     * TB_IGI_CLIENT_CONFIRM insert
     * TB_OUT_ORD client_confirm_yn update
     * ERP 알림톡 API 호출, 넘겨줄 정보: whCd, cstCd, slipNo, godCd, inDiv(1:입고, 2:반품)
     * 입고완료: COMPLETED
     * (입고검수)
     * TB_IN_CHECK insert
     * (입고확정)
     * TB_IN insert
     * TB_IN_WRK_INFO(입고 작업 정보, 입고할증률 포함) insert
     * TB_IN_ORD(작업상태 입고확정으로 변경) update wrk_stat = '3'
     * <p>
     * [필수 정보 조회]
     * -ordDt 입고일자
     * -centerArrivalType 센터도착유형(조기, 정상, 지연)
     * -ReleaseType 출고유형(당일, 익일, D+2)
     * -ReleaseDt 출고가능일
     * -supCd 공급사코드
     **/

    /**
     * 센터도착: ARRIVED
     */
    public UpdateErpPurchaseOrderResponse arriveAtCenter(UpdateErpPurchaseOrderRequest request) {
        PurchaseOrder purchaseOrder = erpPurchaseOrderDataMapper.erpPurchaseOrderRequestToPurchaseOrder(request);
        PurchaseOrderUpdateRequestHelper helper = findHelper(purchaseOrder.getType().getOrderType());

        helper.persistWhenArrived(purchaseOrder);
        helper.sendNotificationWhenArrived(purchaseOrder);

        return erpPurchaseOrderDataMapper.purchaseOrderToUpdateErpPurchaseOrderResponse(purchaseOrder);
    }

    public UpdateErpPurchaseOrderResponse activate(UpdateErpPurchaseOrderRequest request) {
        PurchaseOrder purchaseOrder = erpPurchaseOrderDataMapper.erpPurchaseOrderRequestToPurchaseOrder(request);
        PurchaseOrderUpdateRequestHelper helper = findHelper(purchaseOrder.getType().getOrderType());

        helper.persistWhenActivated(purchaseOrder);

        return erpPurchaseOrderDataMapper.purchaseOrderToUpdateErpPurchaseOrderResponse(purchaseOrder);
    }

    public UpdateErpPurchaseOrderResponse complete(UpdateErpPurchaseOrderRequest request) {
        PurchaseOrder purchaseOrder = erpPurchaseOrderDataMapper.erpPurchaseOrderRequestToPurchaseOrder(request);
        PurchaseOrderUpdateRequestHelper helper = findHelper(purchaseOrder.getType().getOrderType());

        helper.persistWhenCompleted(purchaseOrder);
        helper.sendNotificationWhenCompleted(purchaseOrder);

        return erpPurchaseOrderDataMapper.purchaseOrderToUpdateErpPurchaseOrderResponse(purchaseOrder);
    }

    public UpdateErpPurchaseOrderResponse cancel(UpdateErpPurchaseOrderRequest request) {
        PurchaseOrder purchaseOrder = erpPurchaseOrderDataMapper.erpPurchaseOrderRequestToPurchaseOrder(request);
        PurchaseOrderUpdateRequestHelper helper = findHelper(purchaseOrder.getType().getOrderType());

        helper.persistWhenCancelled(purchaseOrder);

        return erpPurchaseOrderDataMapper.purchaseOrderToUpdateErpPurchaseOrderResponse(purchaseOrder);
    }


    private PurchaseOrderUpdateRequestHelper findHelper(OrderType orderType) {
        for (PurchaseOrderUpdateRequestHelper helper : purchaseOrderUpdateRequestHelperList) {
            if (helper.is(orderType)) {
                return helper;
            }
        }

        throw new PurchaseOrderNotSupportException(new ErrorDetail("OrderType", orderType.name()));
    }

}
