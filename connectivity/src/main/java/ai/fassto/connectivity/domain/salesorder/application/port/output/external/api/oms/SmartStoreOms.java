package ai.fassto.connectivity.domain.salesorder.application.port.output.external.api.oms;

import ai.fassto.connectivity.externalservice.oms.smartstore.dto.SmartStoreRegisterInvoiceResponse;

public interface SmartStoreOms {
    SmartStoreRegisterInvoiceResponse registerInvoice(String slipNo, String orderNo, String cstCd, String invoice, String deliveryCompanyCode);
}
