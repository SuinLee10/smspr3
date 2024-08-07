package com.example.smspr3.config;

import com.example.smspr3.interceptor.DefaultInterceptor;
import com.example.smspr3.util.TokenFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final TokenFactory tokenFactory;

    public WebMvcConfig(TokenFactory tokenFactory){
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("file:" + "C:/workspace/uploadfiles/")
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new DefaultInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/resources/**", "/api/tbuser/logout");

    }

}
