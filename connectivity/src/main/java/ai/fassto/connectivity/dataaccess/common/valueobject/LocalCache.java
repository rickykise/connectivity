package ai.fassto.connectivity.dataaccess.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocalCache {
    CONNECTIVITY_PROPERTY_LIST("$(caffeine-cache.connectivity-property-list)", 64, 24),
    CONNECTIVITY_PROPERTY("$(caffeine-cache.connectivity-property)", 512, 24);

    private final String name;
    private final long maximumSizeByKey;
    private final int expireAfterWriteInHours;
}
