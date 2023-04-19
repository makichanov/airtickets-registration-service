package com.makichanov.audit.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Airtickets Registration System Audit service")
                        .contact(new Contact()
                                .email("vanya133719@gmail.com")
                                .name("Ivan Riabov"))
                );
    }
}
