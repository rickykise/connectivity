package ai.fassto.connectivity.domain.purchaseorder.application.port.output.repository;


import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.PurchaseOrderId;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Item;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository {

    /************
     * 상태 변경
     *************/
    int updateOrderStatus(PurchaseOrder purchaseOrder);

    /************
     * 센터 도착
     *************/
    int updateCenterArrivalInfo(PurchaseOrder purchaseOrder);

    /************
     * 입고 검수
     *************/


    int insertBulkInCheck(PurchaseOrder purchaseOrder);

    int insertBulkDamagedItemClientConfirm(PurchaseOrder purchaseOrder);

    int updateBulkClientConfirmYn(PurchaseOrder purchaseOrder);

    /************
     * 입고 완료
     *************/
    int insertBulkIn(PurchaseOrder purchaseOrder);

    int insertInWrkInfoWhenSales(PurchaseOrder purchaseOrder);

    /************
     * 반품 완료
     *************/
    int insertBulkInWhenReturn(PurchaseOrder purchaseOrder);

    int insertInWrkInfoWhenReturn(PurchaseOrder purchaseOrder);

    /************
     * 입고 취소
     *************/

    /************
     * 조회
     *************/
    Optional<InOrder> findOneInOrdOptional(PurchaseOrder purchaseOrder);

    List<Item> findDamagedItemListBy(PurchaseOrderId purchaseOrderId);

    /************
     * Items
     *************/

    List<ItemSimple> findItemSimplesBy(List<ItemId> itemIdList);

    /************
     * Common API
     *************/
    String getSlipNo(String warehouseCode, String division);
    String getLocNo(String warehouseCode, String zone);

}

