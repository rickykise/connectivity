package ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.salesorder.core.entity.SalesOrder;
import ai.fassto.connectivity.externalservice.wms.solochain.salesorder.valueobject.Result;

import java.util.List;

public interface SalesOrderWms {
    SalesOrder create(SalesOrder salesOrder);

    SalesOrder update(SalesOrder salesOrder);

    SalesOrder delete(SalesOrder salesOrder);

    void createAsAsync(List<SalesOrder> salesOrder);

    void updateAsAsync(List<SalesOrder> salesOrder);

    void deleteAsAsync(List<SalesOrder> salesOrder);
}
