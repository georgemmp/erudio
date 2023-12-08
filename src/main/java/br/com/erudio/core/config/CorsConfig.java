package br.com.erudio.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.originPatterns}")
    private String corsPatterns;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigin = this.corsPatterns.split(",");

        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(allowedOrigin)
                .allowCredentials(true);
    }
}
