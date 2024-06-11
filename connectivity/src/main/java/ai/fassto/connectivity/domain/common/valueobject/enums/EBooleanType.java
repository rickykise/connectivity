package ai.fassto.connectivity.domain.common.valueobject.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum EBooleanType {
    Y, N;

    public static boolean isY(String value) {
        return "Y".equalsIgnoreCase(value);
    }

    public static String toYN(boolean value) {
        return value ? "Y" : "N";
    }

}
