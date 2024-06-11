package ai.fassto.connectivity.externalservice.wms.solochain.common.configuration;

import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "ai.fassto.connectivity.externalservice.wms.solochain")
public class OpenFeignConfiguration {
    @Bean
    Retryer.Default retryer() {
        return new Retryer.Default(500, 1000, 3);
    }
}
