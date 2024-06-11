package ai.fassto.connectivity.domain.scheduler.core.valueobject.enums;


import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;

public enum SchedulerType {
    DAILY_STOCK;

    public static SchedulerType findBy(String name) {
        SchedulerType type = null;
        try {
            type = SchedulerType.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new NoSuchEnumElementException(SchedulerType.class.getSimpleName(), name);
        }

        return type;
    }
}
