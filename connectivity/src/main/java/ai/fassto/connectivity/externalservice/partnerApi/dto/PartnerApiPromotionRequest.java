package ai.fassto.connectivity.externalservice.partnerApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@JsonInclude(NON_NULL)
public class PartnerApiPromotionRequest {
    private String userId; // "CONNECTIVITY"
    private String godCd; // "01232AAAA01",
    private Integer quantity; // 1

}
