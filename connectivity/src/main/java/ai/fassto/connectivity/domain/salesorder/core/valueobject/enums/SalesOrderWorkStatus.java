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
public enum SalesOrderWorkStatus {
    REQUEST("1", "출고요청"),
    ON_PROGRESS("2", "출고작업중"),
    COMPLETED("3", "출고완료"),
    CANCELLED("8", "출고요청취소"),
    OUT_OF_STOCK("9", "재고부족");


    private final String erpCode;
    private final String description;

    private static final Map<String, SalesOrderWorkStatus> statusMapByErpCode =
            Arrays.stream(SalesOrderWorkStatus.values())
                    .collect(Collectors.toMap(SalesOrderWorkStatus::getErpCode, Function.identity()));


    public static SalesOrderWorkStatus findByErpCode(String erpCode) {
        SalesOrderWorkStatus status = statusMapByErpCode.get(erpCode);
        if (status == null) {
            throw new NoSuchEnumElementException(SalesOrderWorkStatus.class.getSimpleName(), erpCode);
        }

        return status;
    }
}