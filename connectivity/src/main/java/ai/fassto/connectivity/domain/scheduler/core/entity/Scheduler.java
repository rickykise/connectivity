package ai.fassto.connectivity.domain.scheduler.core.entity;

import ai.fassto.connectivity.domain.common.entity.AggregateRoot;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerId;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerStatus;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Scheduler extends AggregateRoot<SchedulerId> {

    private SchedulerType type;
    private SchedulerStatus status;
    private String description;
    private String meta;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime reservedAt;

    private Scheduler(Builder builder) {
        setId(builder.taskId);
        type = builder.type;
        status = builder.status;
        description = builder.description;
        meta = builder.meta;
        email = builder.email;
        createdAt = builder.createdAt;
        reservedAt = builder.reservedAt;
    }

    public static final class Builder {
        private SchedulerId taskId;
        private SchedulerType type;
        private SchedulerStatus status;
        private String description;
        private String meta;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime reservedAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder taskId(SchedulerId val) {
            taskId = val;
            return this;
        }

        public Builder type(SchedulerType val) {
            type = val;
            return this;
        }

        public Builder status(SchedulerStatus val) {
            status = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder meta(String val) {
            meta = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder reservedAt(LocalDateTime val) {
            reservedAt = val;
            return this;
        }

        public Scheduler build() {
            return new Scheduler(this);
        }

    }

}
