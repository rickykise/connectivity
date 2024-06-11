package ai.fassto.connectivity.externalservice.oms.smartstore.mapper;

import ai.fassto.connectivity.domain.salesorder.core.valueobject.enums.CourierType;
import ai.fassto.connectivity.externalservice.oms.smartstore.dto.SmartStoreRegisterInvoiceRequest;
import org.springframework.stereotype.Component;

@Component
public class SmartStoreOmsMapper {
    public SmartStoreRegisterInvoiceRequest toSmartStoreRegisterInvoiceRequest(String slipNo, String orderNo, String customerCode, String invoice, String deliveryCompanyCode) {
        return SmartStoreRegisterInvoiceRequest.builder()
                .slipNo(slipNo)
                .orderNo(orderNo)
                .cstCd(customerCode)
                .invoice(invoice)
                .deliveryCompanyCode(CourierType.getErpCodeBy(deliveryCompanyCode))
                .build();
    }
}
