package ai.fassto.connectivity.common.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnvironmentComponent {
    public static final String PROJECT_NAME = "connectivity";
    public static final String VERSION = "v1.0.56";
    public static final String DESCRIPTION = "[v1.0.56] 유통기한관리 상품 출고금지일 고정값 변경";

    private final Environment environment;
    private final ServerProperties serverProperties;

    public void showProject() {
        log.info("");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("  PROJECT NAME: {}", PROJECT_NAME);
        log.info("  VERSION: {}", VERSION);
        log.info("  DESCRIPTION: {}", DESCRIPTION);
        log.info("  PROFILE: {}", getProperties());
        log.info("  TIME ZONE: {}", TimeZone.getDefault().toZoneId());
        log.info("  PORT NO: {}", serverProperties.getPort());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("");
    }

    public String getProperties() {
        return String.join(",", environment.getActiveProfiles());
    }

    // is active profile stars with "prod" ignore case
    public boolean isProd() {
        String properties = getProperties();
        if (StringUtils.isNotBlank(properties) && properties.toLowerCase().startsWith("prod")){
            return true;
        }
        return false;
    }
}
