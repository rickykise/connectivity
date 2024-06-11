package ai.fassto.connectivity.dataaccess.purchaseorder.adapter;


import ai.fassto.connectivity.domain.common.valueobject.id.BaseId;
import ai.fassto.connectivity.domain.common.valueobject.id.ItemId;
import ai.fassto.connectivity.domain.common.valueobject.id.PurchaseOrderId;
import ai.fassto.connectivity.dataaccess.common.CommonService;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.CommonMapper;
import ai.fassto.connectivity.dataaccess.item.entity.GodEntity;
import ai.fassto.connectivity.dataaccess.purchaseorder.dto.InOrdDTO;
import ai.fassto.connectivity.dataaccess.purchaseorder.entity.InOrdEntity;
import ai.fassto.connectivity.dataaccess.purchaseorder.mapper.PurchaseOrderDataAccessMapper;
import ai.fassto.connectivity.dataaccess.purchaseorder.repository.mybatis.PurchaseOrderMapper;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.repository.PurchaseOrderRepository;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.Item;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.ItemSimple;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.InOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {

    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseOrderDataAccessMapper purchaseOrderDataAccessMapper;
    private final CommonMapper commonMapper;
    private final CommonService commonService;

    @Override
    public int updateOrderStatus(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderMapper.updateWorkStatus(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInOrdEntity(purchaseOrder)
        );
    }

    @Override
    public int updateCenterArrivalInfo(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderMapper.cenArvTimeUpdate(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInOrdEntityWhenArrival(purchaseOrder)
        );
    }

    @Override
    public int insertBulkInCheck(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderMapper.insertBulkInCheck(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInCheckBulkInsertDTO(purchaseOrder)
        );
    }

    @Override
    public int insertBulkDamagedItemClientConfirm(PurchaseOrder purchaseOrder) {
        return purchaseOrderMapper.insertBulkDamagedItemClientConfirm(
                purchaseOrderDataAccessMapper.purchaseOrderToClientConfirmBulkInsertDTO(purchaseOrder)
        );
    }

    @Override
    public int updateBulkClientConfirmYn(PurchaseOrder purchaseOrder) {
        return purchaseOrderMapper.updateBulkClientConfirmYn(
                purchaseOrderDataAccessMapper.purchaseOrderToClientConfirmYnDTO(purchaseOrder)
        );
    }

    @Override
    public int insertBulkIn(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderMapper.insertBulkInWhenSales(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInBulkInsertDTOWhenSales(purchaseOrder)
        );
    }

    @Override
    public int insertBulkInWhenReturn(PurchaseOrder purchaseOrder){
        return this.purchaseOrderMapper.insertBulkInWhenReturn(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInBulkInsertDTOWhenReturn(purchaseOrder)
        );
    }

    @Override
    public int insertInWrkInfoWhenSales(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderMapper.insertInWrkInfoWhenSales(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInWrkInfoEntityWhenSales(purchaseOrder)
        );
    }

    @Override
    public int insertInWrkInfoWhenReturn(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderMapper.insertInWrkInfoWhenReturn(
                this.purchaseOrderDataAccessMapper.purchaseOrderToInWrkInfoEntityWhenReturn(purchaseOrder)
        );
    }

    @Override
    public Optional<InOrder> findOneInOrdOptional(PurchaseOrder purchaseOrder) {
        return purchaseOrderDataAccessMapper.inOrdEntityToInOrder(
                findOneInOrdOrNull(
                        purchaseOrderDataAccessMapper.purchaseOrderToInOrdDTO(purchaseOrder)
                )
        );
    }

    @Override
    public List<Item> findDamagedItemListBy(PurchaseOrderId purchaseOrderId) {
        return purchaseOrderDataAccessMapper.clientConfirmEntityToItem(
                purchaseOrderMapper.findDamagedItemListBySlipNo(purchaseOrderId.getValue())
        );
    }

    @Override
    public List<ItemSimple> findItemSimplesBy(List<ItemId> itemIdList) {
        List<String> godCdList = itemIdList.stream().map(BaseId::getValue).toList();
        return purchaseOrderDataAccessMapper.godEntityListToItemSimpleList(commonMapper.findItems(godCdList));
    }

    @Override
    public String getSlipNo(String warehouseCode, String division) {
        return commonService.getSlipNo(purchaseOrderDataAccessMapper.toSlipNoDTO(warehouseCode, division));
    }

    @Override
    public String getLocNo(String warehouseCode, String zone) {
        return commonService.getLocNo(purchaseOrderDataAccessMapper.toLocNoDTO(warehouseCode, zone));
    }

    private InOrdEntity findOneInOrdOrNull(InOrdDTO inOrdDTO) {
        return this.purchaseOrderMapper.findOneInOrd(inOrdDTO);
    }
}
