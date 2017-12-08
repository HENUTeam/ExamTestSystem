package com.henu.examtestsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@ComponentScan
public class ExamtestsystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamtestsystemApplication.class, args);
    }
}