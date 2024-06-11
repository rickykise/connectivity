package ai.fassto.connectivity.domain.workorder.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkOrderStatus {

    ACTIVE("2", "작업중"),
    COMPLETED("3", "완료"),
    CANCELLED("9", "취소");

    private final String erpCode;
    private final String description;

    public static WorkOrderStatus findBy(String code) {
        try {
            return WorkOrderStatus.valueOf(code);
        } catch (Exception e) {
            throw new NoSuchEnumElementException(WorkOrderStatus.class.getSimpleName(), code);
        }
    }
}
