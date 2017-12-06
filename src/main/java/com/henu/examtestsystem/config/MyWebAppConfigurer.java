package com.henu.examtestsystem.config;

import com.henu.examtestsystem.interceptor.UrlAllInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UrlAllInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/login", "/");
        super.addInterceptors(registry);
    }
}
