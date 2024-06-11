package ai.fassto.connectivity.externalservice.wms.solochain.item.dto;

import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.GetUOIConfigByMaterialMaster;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.ProductCategory;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.MMProductClass;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.MaterialMaster;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class MaterialMasterA implements MaterialMaster {
        @JsonProperty("PartNo")
        private String partNo_;

        @JsonProperty("Name")
        private String name_;

        @JsonProperty("Description")
        private String description_;

        @JsonProperty("Type")
        private String type_;
        @JsonProperty("Status")
        private String status_;
        @JsonProperty("minStrTemp") // "-40",
        private String minStrTemp;
        @JsonProperty("maxStrTemp") // "-10",
        private String maxStrTemp;
        @JsonProperty("bufGodYn") // "N",
        private String bufGodYn;
        @JsonProperty("giftDiv") // "01",
        private String giftDiv;
        @JsonProperty("subMate") // "01",
        private String subMate;
        @JsonProperty("ProductBarcode") // "01",
        private String productBarcode_;

        @JsonProperty("MMProductClass")
        private MMProductClass mmProductClass_;

        @JsonProperty("ProductCategory1")
        private ProductCategory productCategory1_;
        @JsonProperty("ProductCategory2")
        private ProductCategory productCategory2_;
        @JsonProperty("ProductCategory3")
        private ProductCategory productCategory3_;

        @JsonProperty("Precision")
        private Integer precision_;

        @JsonProperty("GetUOIConfigByMaterialMaster")
        private GetUOIConfigByMaterialMaster getUOIConfigByMaterialMaster_;
}
