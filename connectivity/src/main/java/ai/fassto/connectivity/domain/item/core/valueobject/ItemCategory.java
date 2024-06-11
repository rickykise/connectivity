package ai.fassto.connectivity.domain.item.core.valueobject;

import org.apache.commons.lang3.StringUtils;

public record ItemCategory(
        String code1,
        String name1,
        String code2,
        String name2
) {
    public boolean isCategory1() {
        return StringUtils.isNotBlank(this.code1) && StringUtils.isNotBlank(this.name1);
    }

    public boolean isCategory2() {
        return StringUtils.isNotBlank(this.code2) && StringUtils.isNotBlank(this.name2);
    }
}
