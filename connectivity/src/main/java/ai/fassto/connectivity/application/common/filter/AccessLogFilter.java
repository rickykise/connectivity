package ai.fassto.connectivity.application.common.filter;

import ai.fassto.connectivity.application.common.dto.accesslog.AccessLog;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessLogFilter implements Filter {
    private final ConnectivityPropertyService propertyService;

    UserAgentAnalyzer userAgentAnalyzer = UserAgentAnalyzer.newBuilder()
            .withFields("DeviceClass", "DeviceName", "OperatingSystemName", "OperatingSystemVersion")
            .withCache(25000) // default: 10000
            .build();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        /* SKIP LOGGING CASES */
        if (!isAllowedUri(request.getRequestURI()) || isSkipContentType(request.getContentType())) {
            chain.doFilter(req, resp);
            return;
        }

        AccessLog accessLog = AccessLog.newAccessLog(request, userAgentAnalyzer);

        /* HEADER LOGGING FOR EVERY CASES */
        if (hasNoRequestBody(request.getMethod())) {
            accessLog.begin();

            chain.doFilter(req, resp);

            accessLog.end(response);
        } else {
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

            accessLog.beginWithRequestBody();

            chain.doFilter(requestWrapper, responseWrapper);

            accessLog.endWithResponseBody(
                    responseWrapper.getStatus(),
                    getRequestBody(requestWrapper),
                    getResponseBody(responseWrapper),
                    propertyService.getRequestParamMaxSize(),
                    propertyService.getResponseParamMaxSize()
            );
        }

        log.info("HTTP ACCESS LOG FILTER: {}", toJson(accessLog));
    }

    private boolean isAllowedUri(String url) {
        return url.startsWith("/api/");
    }

    private boolean hasNoRequestBody(String method) {
        return method.equals(RequestMethod.GET.name()) ||
                method.equals(RequestMethod.DELETE.name());
    }

    private boolean hasRequestBody(String method) {
        return method.equals(RequestMethod.POST.name()) ||
                method.equals(RequestMethod.PUT.name()) ||
                method.equals(RequestMethod.PATCH.name());
    }

    private boolean isSkip(String url) {
        return url.equals("/") ||
                url.contains("favicon.ico") ||
                url.contains("/js") ||
                url.contains("/css") ||
                url.contains("/images") ||
                url.contains("/fonts") ||
                url.contains("/static") ||
                url.contains("/health");
    }

    private boolean isSkipContentType(String contentType) {
        return StringUtils.isNotBlank(contentType) && contentType.startsWith("multipart");
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    String s = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                    s = s.replaceAll("\\r", "");
                    s = s.replaceAll("\\n", "");
                    return s.replaceAll("\\t", "");
                } catch (UnsupportedEncodingException e) {
                    return " - ";
                }
            }
        }
        return " - ";
    }

    private String getResponseBody(final HttpServletResponse response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            wrapper.setCharacterEncoding("UTF-8");
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                wrapper.copyBodyToResponse();
            }
        }
        return null == payload ? " - " : payload;
    }

}
