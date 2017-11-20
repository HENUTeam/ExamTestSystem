package com.henu.examtestsystem;

import com.henu.examtestsystem.teacher.bean.Teacher;
import com.henu.examtestsystem.teacher.bean.User;
import com.henu.examtestsystem.teacher.service.TeacherService;
import com.henu.examtestsystem.teacher.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@SpringBootApplication
@ComponentScan
public class ExamtestsystemApplication {



    public static void main(String[] args) {
        SpringApplication.run(ExamtestsystemApplication.class, args);
    }
}
