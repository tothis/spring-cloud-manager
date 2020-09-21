package com.example.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 李磊
 * @since 1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("任务接口").build();
        return new Docket(DocumentationType.OAS_30)
                .groupName("task")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.task"))
                .paths(PathSelectors.any())
                .build();
    }
}