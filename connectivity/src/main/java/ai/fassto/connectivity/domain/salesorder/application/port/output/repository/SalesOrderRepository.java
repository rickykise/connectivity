package ai.fassto.connectivity.domain.salesorder.application.port.output.repository;

import ai.fassto.connectivity.dataaccess.salesorder.entity.OutOrdSeperatedGodQtyEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.salesorder.core.entity.Consumable;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutInventoryAssign;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutPickOrder;

import java.util.List;
import java.util.Optional;

public interface SalesOrderRepository {
    Optional<OutOrder> findOneOutOrdOptional(SalesOrder salesOrder);
    int updateBulkOutInventoryAssignLotNo(SalesOrder salesOrder);
    int deleteOutInventoryAssign(SalesOrder salesOrder);
    int insertBulkOutInventoryAssign(SalesOrder salesOrder);
    int insertOutPickMap(SalesOrder salesOrder);
    int updateOutPickMap(SalesOrder salesOrder);
    boolean isExistOutPickMap(SalesOrder salesOrder);
    boolean isExistOutOrdSet(SalesOrder salesOrder);
    boolean isExistOutPickOrd(SalesOrder salesOrder);
    boolean isExistOutPack(SalesOrder salesOrder);
    boolean isExistOutInvoice(SalesOrder salesOrder);
    boolean isExistOut(SalesOrder salesOrder);
    boolean isExistOutPackWhSub(SalesOrder salesOrder);
    int insertBulkOutOrdSet(SalesOrder salesOrder);
    int getPickSeq(SalesOrder salesOrder);
    int insertBulkOutPickOrd(SalesOrder salesOrder, int pickSeq);
    int updateBulkOutPickOrdPickQty(SalesOrder salesOrder);
    int updateBulkOutPickOrdPackQty(SalesOrder salesOrder);
    int updateOutPickOrdStatus(SalesOrder salesOrder);
    int insertBulkOutPack(SalesOrder salesOrder);
    int deleteOutPack(SalesOrder salesOrder);
    int deleteOutInvoice(SalesOrder salesOrder);
    int deleteOut(SalesOrder salesOrder);
    int insertBulkOut(SalesOrder salesOrder);
    int insertBulkOutInvoice(SalesOrder salesOrder);
    int updateOutOrdStatus(SalesOrder salesOrder);
    int updateOutOrdParcelCdInvoiceNo(SalesOrder salesOrder);
    int updateOutPackParcelCdInvoiceNo(SalesOrder salesOrder);
    int updateOutOrdOrdDiv(SalesOrder salesOrder, String ordDiv);
    int updateOutPickMapAllocateYn(SalesOrder salesOrder);
    int updateOutOrdParcelCdInvoiceNoToNull(SalesOrder salesOrder);
    int deleteOutOrdSet(SalesOrder salesOrder);
    int deleteOutPickOrd(SalesOrder salesOrder);
    int insertBulkOutPackWhSub(SalesOrder salesOrder);
    int deleteOutPackWhSub(SalesOrder salesOrder);
    Optional<OutPickOrder> findOneOutPickOrdOptional(SalesOrder salesOrder);
    List<OutInventoryAssign> findOutInventoryAssignList(SalesOrder salesOrder);
    List<Consumable> findWareHouseConsumableList(SalesOrder salesOrder);
    String getSlipNo(SalesOrder salesOrder, String division);
    List<ItemSimple> findItemSimplesBy(List<ItemId> itemIdList);
    List<OutOrdSeperatedGodQtyEntity> findSeperatedGodQtyList(SalesOrder salesOrder);


}
