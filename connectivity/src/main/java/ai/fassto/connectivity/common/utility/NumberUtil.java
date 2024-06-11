package ai.fassto.connectivity.common.utility;

public class NumberUtil {

    public static double toDouble(Object value) {
        if (value == null) {
            return 0.0;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            String s = value.toString();
            if (s.length() == 0) {
                return 0.0;
            }
            return Double.parseDouble(s);
        }
    }
}
