package com.xu.nohotel.config;

/**
 * 解决跨域问题
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080", "null")
//                .allowedOrigins("http://localhost:8081", "null")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .allowCredentials(true);
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
