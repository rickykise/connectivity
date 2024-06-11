package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum PurchaseOrderWorkStatus {
    /**
     * tb_in_ord - wrk_stat: '작업상태(1: 입고 반품 예정(지연), 2: 입고 검수 진행, 3: 입고 확정, 4: 입고 완료, 5: 입고 취소)',
     */
    READY("1", "입고 반품 예정(지연)"),
//    RECEIVED("1", "센터 도착"), // todo: ??
    ACTIVE("2", "입고 검수 진행"),
    CONFIRMED("3", "입고 확정"),
    COMPLETED("4", "입고 완료"), // 로케이션 적치
    CANCELLED("5", "입고 취소");

    private final String erpCode;
    private final String description;

    private static final Map<String, PurchaseOrderWorkStatus> codeMapByType =
            Arrays.stream(PurchaseOrderWorkStatus.values())
                    .collect(Collectors.toMap(PurchaseOrderWorkStatus::getErpCode, Function.identity()));

    public static PurchaseOrderWorkStatus findByErpCode(String erpCode) {
        PurchaseOrderWorkStatus s = codeMapByType.get(erpCode);
        if (s == null) {
            throw new NoSuchEnumElementException(PurchaseOrderWorkStatus.class.getSimpleName(), erpCode);
        }
        return s;
    }
}
