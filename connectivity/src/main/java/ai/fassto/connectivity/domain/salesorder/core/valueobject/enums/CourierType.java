package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum CourierType {

    HANJIN("Hanjin", "HANJIN", "한진택배"),
    CJ("CJ", "CJ", "CJ대한통운"),
    LOTTE("Lotte", "LOTTE", "롯데택배"),
    CHAINLOGIS("Chain Logis", "CHAINLOGIS", "체인로지스"),
    ECREMMOCE("Ecremmoce", "ECREMMOCE", "이크레모스"),
    TEAMFRESH("Teamfresh", "TEAMFRESH", "팀프레시");

    private final String solochainCode;
    private final String erpCode;
    private final String description;

    private final static Map<String, CourierType> typeBySolochainCode =
            Arrays.stream(values()).collect(Collectors.toMap(CourierType::getSolochainCode, Function.identity()));
    private final static Map<String, CourierType> typeByErpCode =
            Arrays.stream(values()).collect(Collectors.toMap(CourierType::getErpCode, Function.identity()));


    public static String getErpCodeBy(String solochainCode) {
        String erpCode = toUpperCaseAndRemoveSpace(solochainCode); //Chain Logis -> CHAINLOGIS
        return isContainedInErpCode(erpCode) ? erpCode : null; //택배사코드(erpCode)가 YLP 인경우 null 반환
    }

    public static boolean isCourier(String solochainCode) {
        return isContainedInErpCode(toUpperCaseAndRemoveSpace(solochainCode)); //택배사코드(erpCode)가 YLP 인경우 false 반환
    }

    private static String toUpperCaseAndRemoveSpace(String str) {
        return str.toUpperCase().replaceAll(" ", "");
    }

    public static boolean isContainedInErpCode(String erpCode) {
        return typeByErpCode.containsKey(erpCode);
    }
}
