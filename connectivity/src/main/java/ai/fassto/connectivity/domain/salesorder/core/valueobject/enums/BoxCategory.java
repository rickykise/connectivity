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
public enum BoxCategory {
    /**
     * 출고 사용 부자재 (boxDiv)
     * 취급온도 (상온) -> 1 : 파스토 박스, 2 : 자사 박스, 4 : 파스토 폴리백, 5 : 자사 폴리백
     * 취급온도 (냉장,냉동) -> 7 : 파스토 스티로폼,  8 :자사 스티로폼
     */
    FASSTO_BOX("1","01", false, "파스토 박스"),
    CUSTOMER_BOX("2","02", true, "고객사 종이 박스"),
    FASSTO_POLY_BAG("4","03", false, "파스토 폴리백"),
    CUSTOMER_POLY_BAG("5","04", true, "고객사 폴리백"),
    FASSTO_STYROFOAM("7","05", false, "파스토 스티로폼"),
    CUSTOMER_STYROFOAM("8","06", true,  "고객사 스티로폼"),
    CUSTOMER_COMPLETE_BOX("3","08", true, "고객사[완] 박스");

    private String erpCode;
    private String solochainCode;
    private boolean customerBox;
    private String description;

    private static final Map<String, BoxCategory> categoryMapBySolochainCode =
            Arrays.stream(BoxCategory.values())
                    .collect(Collectors.toMap(BoxCategory::getSolochainCode, Function.identity()));

    public static BoxCategory findBySolochainCode(String solochainCode) {
        BoxCategory type = categoryMapBySolochainCode.get(solochainCode);
        if (type == null) {
            throw new NoSuchEnumElementException(BoxCategory.class.getSimpleName(), String.format("[%s] is invaild boxcategory code", solochainCode));
        }
        return type;
    }




}
