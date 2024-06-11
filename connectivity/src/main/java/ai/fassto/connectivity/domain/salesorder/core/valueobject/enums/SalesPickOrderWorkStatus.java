package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum SalesPickOrderWorkStatus {

    PICKING("1","피킹작업중"),
    PICK_COMPLETE("2","피킹완료"),
    PACK_COMPLETE("3","검수패킹완료"),
    SALES_COMPLETE("4","출고완료"),
    SALES_CANCEL("8","출고취소"),
    OUT_OF_STOCK("9","재고결품부족");

    private String erpCode;
    private String description;

    SalesPickOrderWorkStatus(String erpCode, String description) {
        this.erpCode = erpCode;
        this.description = description;
    }

    private static final Map<String, SalesPickOrderWorkStatus> statusMapByErpCode =
            Arrays.stream(SalesPickOrderWorkStatus.values())
                    .collect(Collectors.toMap(SalesPickOrderWorkStatus::getErpCode, Function.identity()));

    public static SalesPickOrderWorkStatus findByErpCode(String erpCode) {
        SalesPickOrderWorkStatus status = statusMapByErpCode.get(erpCode);
        if (status == null) {
            throw new NoSuchEnumElementException(SalesPickOrderWorkStatus.class.getSimpleName(), erpCode);
        }

        return status;
    }

}
