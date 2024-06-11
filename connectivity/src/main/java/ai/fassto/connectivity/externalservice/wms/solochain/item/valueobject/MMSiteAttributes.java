package ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject;

import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.MaterialMaster;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record MMSiteAttributes(
        @JsonProperty("@attr")
        Attr attr_,

        @JsonProperty("MaterialMaster")
        MaterialMaster materialMaster_,

        @JsonProperty("Site")
        Site site_,

        @JsonProperty("ABCClass")
        ABCClass abcClass_,

        @JsonProperty("ProductionDateReceiving")
        String productionDateReceiving_,

        @JsonProperty("LotNoProduction")
        String lotNoProduction_,

        @JsonProperty("LotNoFormat")
        LotNoFormat lotNoFormat_,

        @JsonProperty("ExpiryDateReceiving")
        String expiryDateReceiving_,

        @JsonProperty("ExpiryDateTracking")
        String expiryDateTracking_,
        @JsonProperty("ExpiryDateProduction")
        String expiryDateProduction_,

        @JsonProperty("ExpiryDateDelay")
        String expiryDateDelay_,

        @JsonProperty("LotNoTracking")
        String lotNoTracking_,

        @JsonProperty("LotNoReceiving")
        String lotNoReceiving_,

        @JsonProperty("PutawayFromReceiving")
        String putawayFromReceiving_,

        @JsonProperty("CustomerExpiryDateTolerance")
        Integer customerExpiryDateTolerance_,
        @JsonProperty("CustomerExpiryDateToleranceMode")
        String customerExpiryDateToleranceMode_
) {
}
