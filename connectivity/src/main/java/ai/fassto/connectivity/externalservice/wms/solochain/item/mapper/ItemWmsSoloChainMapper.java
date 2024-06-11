package ai.fassto.connectivity.externalservice.wms.solochain.item.mapper;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.item.core.entity.Item;
import ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.ItemWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.MaterialMasterA;
import ai.fassto.connectivity.externalservice.wms.solochain.item.dto.MaterialMasterB;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.CustomerRelation;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.Supplier;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.EMeasureUnit.CM;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EStatus.ACTIVE;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EStatus.INACTIVE;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EWeightUnit.GRAM;
import static ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit.EA;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.*;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ESiteType.CUSTOMER;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.ESiteType.ITEM;

@Component
public class ItemWmsSoloChainMapper {
    public ItemWmsSoloChainRequest itemToCreateUpdateItemWmsSoloChainRequest(List<Item> itemList) {
        List<Transaction> transactions = new ArrayList<>();
        for (Item item : itemList) {
            transactions.add(new Transaction(toMaterialMasterA(item)));
            transactions.add(new Transaction(toMaterialMasterB(item)));
        }

        return new ItemWmsSoloChainRequest(transactions);
    }

    private MaterialMasterB toMaterialMasterB(Item item) {
        return MaterialMasterB.builder()
                .partNo_(item.getId().getValue())
                .mmCustomerRelAttributes_(toMMCustomerRelAttributes(item))
                .mmSiteAttributes_(toMMSiteAttributes(item))
                .build();
    }

    /* 2023-03-28 (David GrandMasion - https://fasstoxlidd.slack.com/archives/C03PHNEPFK6/p1679892681290099)
        Lot Number tracking clarification
        In the interface MaterialMaster from FMS to Solochain:
        Case 1: The item has an expiry date. Please fill these nodes.
            ExpiryDateReceiving: “Prompt”,
            ExpiryDateTracking: “Hard”,
            LotNoTracking: “Hard”,
            LotNoReceiving: “Prompt”,

        Case 2: The item does not have an expiry date or a lot numebr assigned by the manufacturer. Please fill these nodes.
            ExpiryDateReceiving: “”,
            ExpiryDateTracking: “Disabled”,
            LotNoTracking: “Disabled”,
            LotNoReceiving: “”,
        ---
     */

    private MMSiteAttributes toMMSiteAttributes(Item item) {
        return MMSiteAttributes.builder()
                .attr_(Attr.builder().action_(OVERRIDE).index_(BY_MATERIAL_MASTER_AND_SITE).build())
                .materialMaster_(new ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.MaterialMaster(item.getId().getValue()))
                .site_(new Site(FASSTO_APPLICATION, SOLOCHAIN_APPLICATION))
                .abcClass_(new ABCClass("A", Site.builder().className_(SOLOCHAIN_APPLICATION).accountNo_(FASSTO_APPLICATION).build()))
                .productionDateReceiving_(item.getMakeDateManaged() == null ? "" : item.getMakeDateManaged() ? PROMPT : NONE)
                .lotNoProduction_(AUTOMATIC)
                .lotNoFormat_(new LotNoFormat(Type.builder().name_(LOT_NUMBER).build(), DATE_VENDOR_PART_NO))

                .expiryDateReceiving_(item.getDistributionTerm().isManaged() ? PROMPT : "")
                .expiryDateTracking_(item.getDistributionTerm().isManaged() ? HARD : DISABLED)
                .lotNoTracking_(item.getDistributionTerm().isManaged() ? HARD : DISABLED)
                .lotNoReceiving_(item.getDistributionTerm().isManaged() ? PROMPT : "")

                .putawayFromReceiving_(FALSE) // put away: 적재
                .customerExpiryDateTolerance_(item.getDistributionTerm().isManaged() ? item.getDistributionTerm().getDaysBeforeOutProhibited() + 1 : 0) // FMS 입력값 + 1 로 통일, 솔로체인은 유통기한 당일까지 계산에 포함시킴
                .customerExpiryDateToleranceMode_(item.getDistributionTerm().isManaged() ? STRICT : DISABLED)
                .build();
    }

    private MMCustomerRelAttributes toMMCustomerRelAttributes(Item item) {
        return MMCustomerRelAttributes.builder()
                .customerExpiryDateTolerance_(item.getDistributionTerm().isManaged() ? item.getDistributionTerm().getDaysBeforeOutProhibited() + 1 : 0)
                .customerExpiryDateToleranceMode_(item.getDistributionTerm().isManaged() ? STRICT : DISABLED)
                .customerRelation_(CustomerRelation.builder()
                        .materialMaster_(new ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.MaterialMaster(item.getId().getValue()))
                        .supplier_(new Supplier(SOLOCHAIN_APPLICATION, FASSTO_APPLICATION))
                        .customer_(new Customer(item.getCustomerId().getValue(), new SiteType(CUSTOMER.getSolochainName())))
                        .build()
                )
                .build();
    }

    private MaterialMasterA toMaterialMasterA(Item item) {
        return MaterialMasterA.builder()
                .partNo_(item.getId().getValue())
                .name_(item.getName())
                .type_(ITEM.getSolochainName())
                .status_(getStatus(item.getActionType()))
                .maxStrTemp(item.getTemperatureManager().maximumTemperature())
                .minStrTemp(item.getTemperatureManager().minimumTemperature())
                .bufGodYn(item.getCushioningMaterialOptionName())
//                .giftDiv(item.getMaterialClassificationName()) // message: 관련해서 ICD의 정의가 존재 하였었지만 솔로체인쪽에 보내지 않기로 함, 추후 필요하게 되면 다시 결정하기로 함 (with PM)
//                .subMate(item.getSubsidiaryMaterialsTypeName())
                .productBarcode_(item.getBarcode())
                .mmProductClass_(new MMProductClass(item.getCompletedBox()))
                .productCategory1_(item.getItemCategory().isCategory1() ? new ProductCategory(new Category(CATEGORY1), item.getItemCategory().code1(), item.getItemCategory().name1()) : null)
                .productCategory2_(item.getItemCategory().isCategory2() ? new ProductCategory(new Category(CATEGORY2), item.getItemCategory().code2(), item.getItemCategory().name2()) : null)
                .productCategory3_(new ProductCategory(new Category(CATEGORY3), item.getTemperatureManager().code(), item.getTemperatureManager().name()))
                .precision_(0) // 소수점 이하자리는 사용하지 않음
                .getUOIConfigByMaterialMaster_(toGetUOIConfigByMaterialMaster(item))
                .build();
    }

    private GetUOIConfigByMaterialMaster toGetUOIConfigByMaterialMaster(Item item) {
        List<UOIConfig> uoiConfigList = new ArrayList<>();
        uoiConfigList.add(UOIConfig.builder()
                .name_(EA.name())
                .status_(EUOIStatus.ACTIVE.getDisplayName()) // suggests to never cancel the "EA" unit, which is the base unit.
                .baseUOIQuantity_(1)
                .dimensionUOM_(new DimensionUOM(CM.getDisplayName()))
                .weightUOM_(new WeightUOM(GRAM.getDisplayName()))
                .gtin_(StringUtils.isBlank(item.getBarcode()) ? null : new GTIN(item.getBarcode()))
                .build()
        );

        return new GetUOIConfigByMaterialMaster(
                Attr.builder().action_(SoloChainConstant.OVERRIDE).parameter_(SoloChainConstant.MATERIAL_MASTER).build(),
                uoiConfigList
        );
    }

    private String getStatus(ActionType actionType) {
        return actionType.isDelete() ? INACTIVE.name() : ACTIVE.name();
    }
}
