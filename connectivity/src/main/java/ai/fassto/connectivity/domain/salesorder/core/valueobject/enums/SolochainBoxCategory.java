package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum SolochainBoxCategory {
    BAG("Bag", "가방"),
    CARTON("Carton", "박스 상자"),
    PALLET("Pallet", "팔레트"),
    SHIPPABLE("Shippable", "배송 가능한 품목"),
    CRATE("Crate", "크레이트"),
    POLY_BAG("Polybag", "폴리백"),
    EPS_STYROFOAM("EPSStyrofoam", "스티로폼"),
    CUSTOMER_CARTON("CustomerCarton", "고객사 종이 박스"),
    CUSTOMER_POLY_BAG("CustomerPolybag", "고객사 폴리백"),
    CUSTOMER_EPS_STYROFOAM("CustomerEPSStyrofoam", "고객사 스티로폼");

    private String solochainCode;
    private String description;

    private static final Map<String, SolochainBoxCategory> categoryMapBySolochainCode =
            Arrays.stream(SolochainBoxCategory.values())
                    .collect(Collectors.toMap(SolochainBoxCategory::getSolochainCode, Function.identity()));


    public static SolochainBoxCategory findBySolochainCode(String solochainCode) {
        SolochainBoxCategory type = categoryMapBySolochainCode.get(solochainCode);
        if (type == null) {
            throw new NoSuchEnumElementException(SolochainBoxCategory.class.getSimpleName(), solochainCode);
        }
        return type;
    }

}
