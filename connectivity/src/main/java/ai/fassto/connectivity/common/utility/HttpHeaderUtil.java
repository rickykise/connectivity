package ai.fassto.connectivity.common.utility;

import ai.fassto.connectivity.domain.common.valueobject.enums.ELanguageCode;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static ai.fassto.connectivity.domain.common.valueobject.enums.ELanguageCode.ENGLISH;
import static ai.fassto.connectivity.domain.common.valueobject.enums.ELanguageCode.KOREAN;

public class HttpHeaderUtil {
    public static ELanguageCode getAcceptLanguageCodeBy(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        if (StringUtils.isBlank(acceptLanguage)) {
            return ENGLISH;
        }

        String firstLang = acceptLanguage.split(",")[0];
        if (StringUtils.isNotBlank(firstLang) && firstLang.contains(KOREAN.getCode())) {
            return KOREAN;
        }

        return ENGLISH;
    }

    public static boolean isAcceptLanguageKorean(HttpServletRequest request) {
        return KOREAN.equals(getAcceptLanguageCodeBy(request));
    }

    public static Map<String, String> toHeaders(String apiKey) {
        Map<String, String> headerMap = new HashMap<>();

        headerMap.put("Content-Type", "application/json;charset=utf-8");
        if (StringUtils.isNotBlank(apiKey)) {
            headerMap.put("x-api-key", apiKey);
        }

        return headerMap;
    }

    public static Map<String, String> toHeaders() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json;charset=utf-8");
        return headerMap;
    }

}
