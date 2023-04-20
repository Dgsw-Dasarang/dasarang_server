package com.project.dasarang.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://www.dasaedu.com", "http://10.80.163.72", "http://10.80.163.72:3000")
                .allowedMethods("*")
                .allowedHeaders("Origin, X-Requested-With, Content-Type, Accept, Authorization, key")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
