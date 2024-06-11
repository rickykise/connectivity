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
public enum SalesOrderStatus {
    ALLOCATED("ALLOCATED","01", "출고지시확정, 할당완료"),
    PICKED("PICKED","02", "피킹 완료"),
    PACKED("PACKED","03", "패킹 완료"),
    SHIPPED("SHIPPED", "04", "출고 완료"),
    OUTSTANDING("OUTSTANDING","05", "출고지시확정 취소, 할당취소");

    private final String status;
    private final String solochainCode;
    private final String description;

    private final static Map<String, SalesOrderStatus> statusBySolochainStatus =
            Arrays.stream(values()).collect(Collectors.toMap(SalesOrderStatus::getStatus, Function.identity()));

    public static SalesOrderStatus findBySolochainStatus(String orderStatus) {
        SalesOrderStatus salesOrderStatus = statusBySolochainStatus.get(orderStatus);
        if (salesOrderStatus == null) {
            throw new NoSuchEnumElementException(SalesOrderStatus.class.getSimpleName(), orderStatus);
        }
        return salesOrderStatus;
    }

    public boolean is(String name) {
        return this.name().equals(name);
    }

    public boolean isNot(String name) {
        return !is(name);
    }
}
