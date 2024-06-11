package ai.fassto.connectivity.domain.workorder.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;

public interface WorkOrderWms {
    WorkOrder create(WorkOrder workOrder);

    WorkOrder update(WorkOrder workOrder);

    WorkOrder delete(WorkOrder workOrder);
}
