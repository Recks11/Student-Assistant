package com.emmanuelirem.studentassistant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
public class WebConfig implements WebFluxConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8081", "http://192.168.99.48:8081")
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT")
                .maxAge(3600);
        registry.addMapping("/auth/**")
                .allowedOrigins("http://localhost:8081", "http://192.168.99.48:8081")
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT")
                .maxAge(3600);

        // Add more mappings...
    }
}
