package ai.fassto.connectivity.externalservice.oms.smartstore.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmartStoreRegisterInvoiceRequest {
    String slipNo;
    String orderNo;
    String cstCd;
    String invoice;
    String deliveryCompanyCode;
}
