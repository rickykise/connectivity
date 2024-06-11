package ai.fassto.connectivity.domain.salesorder.application;

import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelResponse;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderResponse;
import ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.oms.SmartStoreOms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Component
@RequiredArgsConstructor
@Slf4j
public class OmsSmartStoreWhenAllocatedHandler {
    public static final String SMART_STORE_PREFIX = "smst_";
    private final SmartStoreOms smartStoreOms;


    public void registerInvoiceWhenOmsSmartStore(
            UpdateErpSalesOrderResponse allocated,
            TmsPreCallParcelResponse tmsPreCallParcelResponse
    ) {
        try {
            log.info("To register invoice to OMS Smart Store: {}, {}", toJson(allocated), toJson(tmsPreCallParcelResponse));
            if (StringUtils.isNotBlank(allocated.orderNo())
                    && allocated.orderNo().startsWith(SMART_STORE_PREFIX)
                    && tmsPreCallParcelResponse != null
                    && tmsPreCallParcelResponse.solh_() != null
                    && StringUtils.isNotBlank(tmsPreCallParcelResponse.solh_().trackingNumber_())
            ) {
                smartStoreOms.registerInvoice(
                        allocated.salesOrderId(),
                        allocated.orderNo(),
                        allocated.customerCode(),
                        tmsPreCallParcelResponse.solh_().trackingNumber_(),
                        tmsPreCallParcelResponse.deliveryCompanyCode()
                );
            }
        } catch (Exception e) {
            log.error("[SalesOrder] Sending to register invoice to OMS Smart Store was failed.", e);
        }
    }
}
