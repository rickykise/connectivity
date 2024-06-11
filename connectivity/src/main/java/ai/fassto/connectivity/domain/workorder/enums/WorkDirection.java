package ai.fassto.connectivity.domain.workorder.enums;


import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum WorkDirection {
    FROM("2"), TO("1");

    private final String code;

    public String getCode() {
        return this.code;
    }

    private final static Map<String, WorkDirection> stringToEnum = new HashMap<>();

    static {
        for (WorkDirection itemRole : values()) {
            stringToEnum.put(itemRole.getCode(), itemRole);
        }
    }

    public static WorkDirection findBy(String value) {
        return stringToEnum.get(value);
    }

    public static boolean isFrom(String code) {
        return FROM.getCode().equals(code);
    }

    public static boolean isTo(String code) {
        return TO.getCode().equals(code);
    }
}
