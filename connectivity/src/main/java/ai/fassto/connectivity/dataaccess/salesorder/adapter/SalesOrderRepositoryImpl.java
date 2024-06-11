package ai.fassto.connectivity.dataaccess.salesorder.adapter;

import ai.fassto.connectivity.dataaccess.salesorder.dto.OutPickOrdDTO;
import ai.fassto.connectivity.dataaccess.salesorder.entity.OutOrdSeperatedGodQtyEntity;
import ai.fassto.connectivity.dataaccess.salesorder.entity.OutPickOrdEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.BaseId;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.dataaccess.common.CommonService;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.CommonMapper;
import ai.fassto.connectivity.dataaccess.salesorder.dto.OutOrdDTO;
import ai.fassto.connectivity.dataaccess.salesorder.entity.OutOrdEntity;
import ai.fassto.connectivity.dataaccess.salesorder.mapper.SalesOrderDataAccessMapper;
import ai.fassto.connectivity.dataaccess.salesorder.repository.mybatis.SalesOrderMapper;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.salesorder.application.port.output.repository.SalesOrderRepository;
import ai.fassto.connectivity.domain.salesorder.core.entity.Consumable;
import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutInventoryAssign;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutOrder;
import ai.fassto.connectivity.domain.salesorder.core.valueobject.OutPickOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SalesOrderRepositoryImpl implements SalesOrderRepository {

    private final SalesOrderMapper salesOrderMapper;
    private final SalesOrderDataAccessMapper salesOrderDataAccessMapper;
    private final CommonMapper commonMapper;
    private final CommonService commonService;

    @Override
    public Optional<OutOrder> findOneOutOrdOptional(SalesOrder salesOrder) {
        return salesOrderDataAccessMapper.outOrdEntityToOutOrder(
                findOneOutOrdOrNull(
                    salesOrderDataAccessMapper.salesOrderToOutOrdDTO(salesOrder)
                )
        );
    }

    @Override
    public int updateBulkOutInventoryAssignLotNo(SalesOrder salesOrder) {
        return salesOrderMapper.updateBulkOutInventoryAssignLotNo(
                salesOrderDataAccessMapper.salesOrderToOutInventoryAssignLotNoBulkUpdateDTO(salesOrder)
        );
    }

    @Override
    public int deleteOutInventoryAssign(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOutInventoryAssign(
                salesOrderDataAccessMapper.salesOrderToOutInventoryAssignDeleteDTO(salesOrder)
        );
    }

    @Override
    public int insertBulkOutInventoryAssign(SalesOrder salesOrder) {
        return salesOrderMapper.insertBulkOutInventoryAssign(
                salesOrderDataAccessMapper.salesOrderToOutInventoryAssignBulkInsertDTO(salesOrder)
        );
    }

    @Override
    public int insertOutPickMap(SalesOrder salesOrder) {
        return salesOrderMapper.insertOutPickMap(
                salesOrderDataAccessMapper.salesOrderToOutPickMapEntity(salesOrder)
        );
    }

    @Override
    public int updateOutPickMap(SalesOrder salesOrder) {
        return salesOrderMapper.updateOutPickMap(
                salesOrderDataAccessMapper.salesOrderToOutPickMapEntity(salesOrder)
        );
    }

    @Override
    public boolean isExistOutPickMap(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOutPickMap(
                salesOrderDataAccessMapper.salesOrderToOutPickMapEntity(salesOrder)
        );
    }

    @Override
    public boolean isExistOutOrdSet(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOutOrdSet(
                salesOrderDataAccessMapper.salesOrderToOutOrdSetExistDTO(salesOrder)
        );
    }

    @Override
    public boolean isExistOutPickOrd(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOutPickOrd(
                salesOrderDataAccessMapper.salesOrderToOutPickOrdExistDTO(salesOrder)
        );
    }

    @Override
    public boolean isExistOutPack(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOutPack(
                salesOrderDataAccessMapper.salesOrderToOutPackExistDTO(salesOrder)
        );
    }

    @Override
    public boolean isExistOutInvoice(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOutInvoice(
                salesOrderDataAccessMapper.salesOrderToOutInvoiceExistDTO(salesOrder)
        );
    }

    @Override
    public boolean isExistOut(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOut(
                salesOrderDataAccessMapper.salesOrderToOutExistDTO(salesOrder)
        );
    }

    @Override
    public boolean isExistOutPackWhSub(SalesOrder salesOrder) {
        return salesOrderMapper.isExistOutPackWhSub(
                salesOrderDataAccessMapper.salesOrderToOutPackWhSubExistDTO(salesOrder)
        );
    }

    @Override
    public int insertBulkOutOrdSet(SalesOrder salesOrder) {
        return salesOrderMapper.insertBulkOutOrdSet(
                salesOrderDataAccessMapper.salesOrderToOutOrdSetBulkInsertDTO(salesOrder)
        );
    }

    @Override
    public int getPickSeq(SalesOrder salesOrder) {
        return salesOrderMapper.getPickSeq(
                salesOrderDataAccessMapper.salesOrderToPickSeqDTO(salesOrder)
        );
    }

    @Override
    public int insertBulkOutPickOrd(SalesOrder salesOrder, int pickSeq) {
        return salesOrderMapper.insertBulkOutPickOrd(
                salesOrderDataAccessMapper.salesOrderToOutPickOrdBulkInsertDTO(salesOrder, pickSeq)
        );
    }

    @Override
    public int updateBulkOutPickOrdPickQty(SalesOrder salesOrder) {
        return salesOrderMapper.updateBulkOutPickOrdPickQty(
                salesOrderDataAccessMapper.salesOrderToOutPickOrdPickQtyBulkUpdateDTO(salesOrder)
        );
    }

    @Override
    public int updateBulkOutPickOrdPackQty(SalesOrder salesOrder) {
        return salesOrderMapper.updateBulkOutPickOrdPackQty(
                salesOrderDataAccessMapper.salesOrderToOutPickOrdPackQtyBulkUpdateDTO(salesOrder)
        );
    }

    @Override
    public int updateOutPickOrdStatus(SalesOrder salesOrder) {
        return salesOrderMapper.updateOutPickOrdWorkStatus(
                salesOrderDataAccessMapper.salesOrderToOutPickOrdEntity(salesOrder)
        );
    }

    @Override
    public int insertBulkOutPack(SalesOrder salesOrder) {
        salesOrder.setOutOrdEntity(salesOrderMapper.findOneOutOrd(
                salesOrderDataAccessMapper.salesOrderToOutOrdDTO(salesOrder)));

        return salesOrderMapper.insertBulkOutPack(
                salesOrderDataAccessMapper.salesOrderToOutPackBulkInsertDTO(salesOrder)
        );
    }

    @Override
    public int deleteOutPack(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOutPack(
                salesOrderDataAccessMapper.salesOrderToOutPackDeleteDTO(salesOrder)
        );
    }

    @Override
    public int deleteOutInvoice(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOutInvoice(
                salesOrderDataAccessMapper.salesOrderToOutInvoiceExistDTO(salesOrder)
        );
    }

    @Override
    public int deleteOut(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOut(
                salesOrderDataAccessMapper.salesOrderToOutDeleteDTO(salesOrder)
        );
    }

    @Override
    public int insertBulkOut(SalesOrder salesOrder) {
        return salesOrderMapper.insertBulkOut(
                salesOrderDataAccessMapper.salesOrderToOutBulkInsertDTO(salesOrder)
        );
    }

    @Override
    public int insertBulkOutInvoice(SalesOrder salesOrder) {
        return salesOrderMapper.insertBulkOutInvoice(
                salesOrderDataAccessMapper.salesOrderToOutInvoiceBulkInsertDTO(salesOrder)
        );
    }

    @Override
    public int updateOutOrdStatus(SalesOrder salesOrder) {
        return salesOrderMapper.updateOutOrdStatus(
                salesOrderDataAccessMapper.salesOrderToOutOrdEntity(salesOrder)
        );
    }

    @Override
    public int updateOutOrdParcelCdInvoiceNo(SalesOrder salesOrder) {
        return salesOrderMapper.updateOutOrdParcelCdInvoiceNo(
                salesOrderDataAccessMapper.salesOrderToOutOrdParcelCdInvoiceNoUpdateDTO(salesOrder)
        );
    }

    @Override
    public int updateOutPackParcelCdInvoiceNo(SalesOrder salesOrder) {
        return salesOrderMapper.updateBulkOutPackParcelCdInvoiceNo(
                salesOrderDataAccessMapper.salesOrderToOutPackParcelCdInvoiceNoBulkUpdateDTO(salesOrder)
        );
    }

    @Override
    public int updateOutOrdOrdDiv(SalesOrder salesOrder, String ordDiv) {
        return salesOrderMapper.updateOutOrdOrdDiv(
                salesOrderDataAccessMapper.salesOrderToOutOrdOrdDivDTO(salesOrder, ordDiv)
        );
    }

    @Override
    public int updateOutPickMapAllocateYn(SalesOrder salesOrder) {
        return salesOrderMapper.updateOutPickMapAllocateYn(
                salesOrderDataAccessMapper.salesOrderToOutPickMapStatusDTO(salesOrder)
        );
    }

    @Override
    public int updateOutOrdParcelCdInvoiceNoToNull(SalesOrder salesOrder) {
        return salesOrderMapper.updateOutOrdParcelCdInvoiceNoToNull(
                salesOrderDataAccessMapper.salesOrderToOutOrdParcelCdInvoiceNoToNullDTO(salesOrder)
        );
    }

    @Override
    public int deleteOutOrdSet(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOutOrdSet(
                salesOrderDataAccessMapper.salesOrderToOutOrdSetDeleteDTO(salesOrder)
        );
    }

    @Override
    public int deleteOutPickOrd(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOutPickOrd(
                salesOrderDataAccessMapper.salesOrderToOutPickOrdDeleteDTO(salesOrder)
        );
    }

    @Override
    public int insertBulkOutPackWhSub(SalesOrder salesOrder) {
        return salesOrderMapper.insertBulkOutPackWhSub(
                salesOrderDataAccessMapper.salesOrderToOutPackWhSubBulkInsertDTO(salesOrder)
        );
    }

    @Override
    public int deleteOutPackWhSub(SalesOrder salesOrder) {
        return salesOrderMapper.deleteOutPackWhSub(
                salesOrderDataAccessMapper.salesOrderToOutPackWhSubDeleteDTO(salesOrder)
        );
    }

    @Override
    public Optional<OutPickOrder> findOneOutPickOrdOptional(SalesOrder salesOrder) {
        return salesOrderDataAccessMapper.outOrdEntityToOutPickOrder(
                findOneOutPickOrdOrNull(
                        salesOrderDataAccessMapper.salesOrderToOutPickOrdDTO(salesOrder)
                )
        );
    }

    @Override
    public List<OutInventoryAssign> findOutInventoryAssignList(SalesOrder salesOrder) {
        return salesOrderDataAccessMapper.outInventoryAssignEntityListToOutInventoryAssignList(
                salesOrderMapper.findOutInventoryAssignList(salesOrder.getId().getValue())
        );
    }

    @Override
    public List<Consumable> findWareHouseConsumableList(SalesOrder salesOrder) {
        return salesOrderDataAccessMapper.whSubsidiaryEntityListToWareHouseConsumableList(
                salesOrderMapper.findWhSubsidiaryList(
                        salesOrderDataAccessMapper.salesOrderToWareHouseConsumableDTO(salesOrder)
                )
        );
    }

    @Override
    public String getSlipNo(SalesOrder salesOrder, String division) {
        return commonService.getSlipNo(salesOrderDataAccessMapper.toSlipNoDTO(salesOrder, division));
    }

    @Override
    public List<ItemSimple> findItemSimplesBy(List<ItemId> itemIdList) {
        List<String> godCdList = itemIdList.stream().map(BaseId::getValue).toList();
        return salesOrderDataAccessMapper.godEntityListToItemSimpleList(commonMapper.findItems(godCdList));
    }

    private OutOrdEntity findOneOutOrdOrNull(OutOrdDTO outOrdDTO){
        return salesOrderMapper.findOneOutOrd(outOrdDTO);
    }

    private OutPickOrdEntity findOneOutPickOrdOrNull(OutPickOrdDTO outPickOrdDTO){
        return salesOrderMapper.findOneOutPickOrd(outPickOrdDTO);
    }

    @Override
    public List<OutOrdSeperatedGodQtyEntity> findSeperatedGodQtyList(SalesOrder salesOrder) {
        return salesOrderMapper.findSeperatedGodQtyList(
                salesOrderDataAccessMapper.findSeperatedGodQtyDTO(salesOrder)
        );
    }
}
