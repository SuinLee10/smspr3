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
        //ResourceHandlerRegistry => Resources served out of locations under web application root, from the class path and others
        WebMvcConfigurer.super.addResourceHandlers(registry); //To create resource handler
        registry
                .addResourceHandler("/files/**")//Url path patterns
                .addResourceLocations("file:" + "C:/workspace/uploadfiles/")
                .setCachePeriod(60 * 60)   // 캐시를 사용할지 말지를 결정. 운영중이라면 true, 개발중이라면 false가 적절
                .resourceChain(true)  // 요청에 해당하는 리소스를 찾는 방법
                .addResolver(new PathResourceResolver()); // 응답으로 내보낼 리소스를 변경하는 방법
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //Helps with configuring a list of mapped interceptors. => handle interceptor
        registry.addInterceptor(new DefaultInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/resources/**");
    }

}
