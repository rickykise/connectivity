package ai.fassto.connectivity.externalservice.oms.smartstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmartStoreRegisterInvoiceResponse {
    private String slipNo;
    private String orderNo;
    private String cstCd;
    private String invoice;
    private String deliveryCompanyCode; // 추가 (택배사 코드)
    private String result; // 통신 결과 값
}
