package ai.fassto.connectivity.domain.scheduler.core.valueobject;



import ai.fassto.connectivity.domain.common.valueobject.id.BaseId;

import java.util.UUID;

public class SchedulerId extends BaseId<UUID> {
    public SchedulerId(UUID value) {
        super(value);
    }
}
