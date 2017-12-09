package com.henu.examtestsystem.config;

import com.henu.examtestsystem.interceptor.UrlAllInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    UrlAllInterceptor urlAllInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlAllInterceptor)
                .addPathPatterns("/**").
                excludePathPatterns("/login", "/", "/togo", "/css/**", "/images/**", "/js/**", "/exams/**", "/files/**");
        super.addInterceptors(registry);
    }

}
