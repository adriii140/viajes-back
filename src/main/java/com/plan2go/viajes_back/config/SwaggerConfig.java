// src/main/java/com/plan2go/viajes_back/config/SwaggerConfig.java
package com.plan2go.viajes_back.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI viajesOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("API Plan2Go - Viajes")
            .description("Documentación de endpoints del backend 'viajes-back'")
            .version("1.0.0"));
  }
}
