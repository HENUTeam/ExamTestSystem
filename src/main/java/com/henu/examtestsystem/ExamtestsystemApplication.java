package com.henu.examtestsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 高逸博
 */
@Controller
@SpringBootApplication
@ComponentScan
@EnableTransactionManagement
public class ExamtestsystemApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ExamtestsystemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(applicationClass);
    }

    private static Class applicationClass = ExamtestsystemApplication.class;
}
