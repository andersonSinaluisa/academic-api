package com.andersonsinaluisa.academicapi.shared.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
@Configuration
public class CorsGlobalConfig  implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // permite todas las rutas
                .allowedOrigins("http://localhost:5173") // tu frontend
                .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
