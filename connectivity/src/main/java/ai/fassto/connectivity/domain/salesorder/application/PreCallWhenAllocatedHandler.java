package ai.fassto.connectivity.domain.salesorder.application;

import ai.fassto.connectivity.dataaccess.salesorder.dto.TmsPreCallParcelDTO;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.parcel.application.port.input.service.TmsParcelApplicationService;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import ai.fassto.connectivity.domain.salesorder.application.mapper.PreCallWhenAllocatedDataMapper;
import ai.fassto.connectivity.domain.salesorder.application.port.output.repository.PreCallWhenAllocatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PreCallWhenAllocatedHandler {
    private final TmsParcelApplicationService tmsParcelApplicationService;
    private final PreCallWhenAllocatedRepository preCallWhenAllocatedRepository;
    private final PreCallWhenAllocatedDataMapper preCallWhenAllocatedDataMapper;

    public TmsPreCallParcelResponse sendPreCall(UpdateErpSalesOrderRequest request) {
        try {
            TmsPreCallParcelDTO preCallInfoDto = preCallWhenAllocatedRepository.getPreCallInfo(
                    request.getSlipNo(), request.getWarehouseCode(), request.getCustomerCode()
            );
            if (preCallInfoDto == null) {
                log.info("[SalesOrder] Sending pre call to TMS was skipped. No pre call info was found.");
                return null;
            }

            return tmsParcelApplicationService.createTmsPreCall(
                    preCallWhenAllocatedDataMapper.toTmsPreCallParcelRequest(preCallInfoDto, request)
            );
        } catch (Exception e) {
            log.error("[SalesOrder] Sending pre call to TMS was failed.", e);
        }
        return null;
    }
}
