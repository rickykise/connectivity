package ai.fassto.connectivity.application.common.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    private static final String GROUP_NAME_TO_SOLOCHAIN = "To Solochain APIs";
    private static final String GROUP_NAME_TO_ERP = "To ERP APIs";
    private static final String GROUP_NAME_TO_CARRIER = "To CARRIER APIs";


    @Bean
    public Docket erpToSoloChain() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME_TO_SOLOCHAIN)
                .apiInfo(new ApiInfoBuilder()
                        .title("[CONNECTIVITY] TO SOLOCHAIN API DOCUMENT")
                        .version("0.0.1")
                        .description("APIs to SOLOCHAIN")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.fassto.connectivity.application.rest"))
                .paths(PathSelectors.regex("/api/solochain/.*"))
                .build();
    }

    @Bean
    public Docket soloChainToErp() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME_TO_ERP)
                .apiInfo(new ApiInfoBuilder()
                        .title("[CONNECTIVITY] TO ERP API DOCUMENT")
                        .version("0.0.1")
                        .description("APIs TO ERP")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.fassto.connectivity.application.rest"))
                .paths(PathSelectors.regex("/api/erp/.*"))
                .build();
    }

    @Bean
    public Docket soloChainToCarrier() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME_TO_CARRIER)
                .apiInfo(new ApiInfoBuilder()
                        .title("[CONNECTIVITY] TO CARRIER API DOCUMENT")
                        .version("0.0.1")
                        .description("APIs TO CARRIER")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.fassto.connectivity.application.rest"))
                .paths(PathSelectors.regex("/api/carrier/.*"))
                .build();
    }
}
