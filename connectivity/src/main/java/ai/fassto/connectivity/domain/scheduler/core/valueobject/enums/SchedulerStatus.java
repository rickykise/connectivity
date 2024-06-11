package ai.fassto.connectivity.domain.scheduler.core.valueobject.enums;


import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;

public enum SchedulerStatus {
    REGISTERED,
    IN_PROGRESS,
    COMPLETED,
    FAILED,
    CANCELLED;

    public static SchedulerStatus findBy(String name) {
        SchedulerStatus status = null;
        try {
            status = SchedulerStatus.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new NoSuchEnumElementException(SchedulerStatus.class.getSimpleName(), name);
        }

        return status;
    }

}
