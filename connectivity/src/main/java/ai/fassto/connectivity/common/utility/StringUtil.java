package ai.fassto.connectivity.common.utility;

public class StringUtil {
    /**
     * 핸드폰번호 유효성 검사.
     */
    public static boolean isHpNumberFormat(String phoneNumber)  {
        phoneNumber = phoneNumber.replaceAll("-", "");

        if (!isNumber(phoneNumber) || !phoneNumber.startsWith("010") || isEmptyString(phoneNumber))
            return false;

        return true;
    }

    /** 유효한 숫자인지 체크 */
    public static boolean isNumber(String str) {
        boolean result = false;
        try {
            Double.parseDouble(str);
            result = true;
        } catch(Exception e){
        }

        return result;
    }

    public static boolean isEmptyString(String val) {
        if (val == null || val.equalsIgnoreCase("") || val.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
