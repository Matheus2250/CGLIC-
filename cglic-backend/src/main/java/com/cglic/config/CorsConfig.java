// src/main/java/com/cglic/config/CorsConfig.java
package com.cglic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir origens específicas
        config.addAllowedOrigin("http://localhost:3000"); // URL do frontend React
        
        // Permitir cookies
        config.setAllowCredentials(true);
        
        // Permitir headers específicos
        config.addAllowedHeader("*");
        
        // Permitir métodos específicos
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}