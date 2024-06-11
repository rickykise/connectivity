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
//출고유형(1:정상(출고),2:고객사재고이관,3:기타출고,4:반출,9:예외)
public enum OutType {
    NORMAL("1", "정상(출고)"),
    CUSTOMER("2", "고객사재고이관"),
    OTHER("3", "기타출고"),
    CARRY_OUT("4", "반출"),
    UNKNOWN("5", "???"),
    ETC("9", "예외");

    private static final Map<String, OutType> tpMapByErpCode =
            Arrays.stream(OutType.values())
                    .collect(Collectors.toMap(OutType::getErpCode, Function.identity()));

    private final String erpCode;
    private final String description;

    public static OutType findByErpCode(Integer erpCode) {
        OutType outType = tpMapByErpCode.get(String.valueOf(erpCode));
        if (outType == null) {
            throw new NoSuchEnumElementException(OutType.class.getSimpleName(), String.valueOf(erpCode));
        }
        return outType;
    }

}
