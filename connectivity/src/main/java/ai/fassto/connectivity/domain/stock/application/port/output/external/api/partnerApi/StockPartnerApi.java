package ai.fassto.connectivity.domain.stock.application.port.output.external.api.partnerApi;


import ai.fassto.connectivity.dataaccess.stock.entity.WarehouseInventoryPartnerApiEntity;

import java.util.List;

public interface StockPartnerApi {

    void create(List<WarehouseInventoryPartnerApiEntity> WarehouseInventoryPartnerApiEntityList);

}
