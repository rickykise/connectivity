package ai.fassto.connectivity.application.common.dto.accesslog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Slf4j
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AccessLog {

    private String threadId;
    private String method;
    private String url;
    private String authorization;
    private String userAgent;
    private UserAgentInfo userAgentInfo;
    private String referer;
    private String host;
    private String clientIp;

    private String requestParams;
    private String responseParams;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime requestAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    private LocalDateTime responseAt;

    @JsonIgnore
    private Long requestAtAsTimestamp;
    private Integer elapsedTimeInMS;
    private int httpStatus;

    public static AccessLog newAccessLog(HttpServletRequest request, UserAgentAnalyzer userAgentAnalyzer) {
        AccessLog accessLog = new AccessLog();
        accessLog.setThreadId(Thread.currentThread().getName());
        accessLog.setAuthorization(request.getHeader("Authorization"));
        accessLog.setUserAgent(request.getHeader("user-agent"));
        accessLog.setReferer(request.getHeader("referer"));
        accessLog.setHost(request.getHeader("host"));
        accessLog.setClientIp(IpUtils.getIpAddress(request));
        accessLog.setMethod(request.getMethod());
        accessLog.setUrl(request.getRequestURI());

        if (isNotBlank(accessLog.getUserAgent())) {
            UserAgent agent = userAgentAnalyzer.parse(accessLog.getUserAgent());
            UserAgentInfo userAgentInfo = new UserAgentInfo();
            userAgentInfo.setDeviceClass(agent.getValue("DeviceClass"));
            userAgentInfo.setDeviceName(agent.getValue("DeviceName"));
            userAgentInfo.setOsName(agent.getValue("OperatingSystemName"));
            userAgentInfo.setOsVersion(agent.getValue("OperatingSystemVersion"));
            accessLog.setUserAgentInfo(userAgentInfo);
        }

        return accessLog;
    }

    public void begin() {
        requestAt = LocalDateTime.now();
        requestAtAsTimestamp = System.currentTimeMillis();
    }
    public void end(HttpServletResponse response) {
        responseAt = LocalDateTime.now();
        elapsedTimeInMS = (int) (System.currentTimeMillis() - requestAtAsTimestamp);
        httpStatus = response.getStatus();
    }
    public void beginWithRequestBody() {
        requestAt = LocalDateTime.now();
        requestAtAsTimestamp = System.currentTimeMillis();
    }

    public void endWithResponseBody(int status, String requestParams, String responseParams, int requestMaxLength, int responseMaxLength) {
        responseAt = LocalDateTime.now();
        elapsedTimeInMS = (int) (System.currentTimeMillis() - requestAtAsTimestamp);
        httpStatus = status;

        if (StringUtils.isNotBlank(requestParams)) {
            this.requestParams = requestParams.substring(0, Math.min(requestParams.length(), requestMaxLength));
            if (requestParams.length() > requestMaxLength) {
                this.responseParams += " ...... ";
            }
        }
        if (StringUtils.isNotBlank(responseParams)) {
            this.responseParams = responseParams.substring(0, Math.min(responseParams.length(), responseMaxLength));
            if (responseParams.length() > responseMaxLength) {
                this.responseParams += " ...... ";
            }
        }
    }
}
