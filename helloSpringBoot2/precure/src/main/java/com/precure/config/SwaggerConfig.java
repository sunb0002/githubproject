package com.precure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${app.service-name:pcr_name}")
    private String svcName;
    @Value("${app.service-desc:pcr_desc}")
    private String svcDesc;
    @Value("${app.enable-swagger}")
    private boolean enableSwagger;

    @Bean
    public Docket createApi() {
        ApiInfo apiInfo = new ApiInfoBuilder().title(svcName)
                .description(svcDesc).build();
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.precure"))
                .paths(PathSelectors.any())
                .build();
    }

}
