package ai.fassto.connectivity.domain.purchaseorder.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderResponse;
import ai.fassto.connectivity.domain.purchaseorder.application.mapper.SolochainPurchaseOrderDataMapper;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.external.api.wms.PurchaseOrderWms;
import ai.fassto.connectivity.domain.purchaseorder.application.dto.solochain.SoloChainPurchaseOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseOrderCreateRequestHandler {
    private final SolochainPurchaseOrderDataMapper solochainPurchaseOrderDataMapper;
    private final PurchaseOrderWms purchaseOrderWms;

    public SoloChainPurchaseOrderResponse createPurchaseOrder(SoloChainPurchaseOrderRequest request) {
        return solochainPurchaseOrderDataMapper.purchaseOrderToSoloChainPurchaseOrderResponse(
                purchaseOrderWms.create(
                        solochainPurchaseOrderDataMapper.soloChainPurchaseOrderRequestToPurchaseOrder(request, ActionType.CREATE)
                )
        );
    }
}
