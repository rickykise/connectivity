package ai.fassto.connectivity.application.common.dto.accesslog;

import lombok.Data;

@Data
public class UserAgentInfo {

    private String deviceName;  // Apple iPhone
    private String osName;      // iOS
    private String osVersion;   // 12.3.1
    private String deviceClass; // Phone
}