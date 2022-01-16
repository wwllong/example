package com.example.itoken.common.web.config;

import com.example.itoken.common.web.interceptor.ConstantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConstantInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }

}
