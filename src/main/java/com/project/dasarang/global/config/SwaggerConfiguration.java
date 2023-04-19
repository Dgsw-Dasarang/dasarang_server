package com.project.dasarang.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("다사랑 API")
                .version("version 1.0")
                .description("다사랑 API 문서");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(
                                "bearer-token",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")))
                .info(info);
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return OpenApi -> OpenApi
                .getPaths().values().stream()
                .flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation
                        .addParametersItem(new HeaderParameter()
                                .name("key")
                                .required(Boolean.TRUE)
                                )
                        );
    }

}
