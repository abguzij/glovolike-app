package com.example.glovolike_menthors.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("Glovo-like Web-приложение")
                .version("1.0")
                .description("Учебный проект для изучения основ работы со Spring Boot");
        return new OpenAPI().info(info);
    }
}
