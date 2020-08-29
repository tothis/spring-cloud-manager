package com.example.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置 https://github.com/springfox/springfox
 *
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket admin() {
        // http://localhost:8080/swagger-ui/
        ApiInfo apiInfo = new ApiInfoBuilder().title("后台").build();
        return new Docket(DocumentationType.OAS_30)
                .groupName("admin")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.admin"))
                .paths(PathSelectors.any())
                .build();
    }
}