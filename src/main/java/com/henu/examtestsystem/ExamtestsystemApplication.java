package com.henu.examtestsystem;

import com.henu.examtestsystem.bean.Teacher;
import com.henu.examtestsystem.bean.User;
import com.henu.examtestsystem.service.TeacherService;
import com.henu.examtestsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@SpringBootApplication
public class ExamtestsystemApplication {

    Logger logger = LoggerFactory.getLogger(ExamtestsystemApplication.class);
    @Resource
    UserService userService;
    @Resource
    TeacherService teacherService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(ModelMap modelMap, String name, String password) {
        logger.info("---------name:{},password:{}", name, password);
        Teacher teacher = teacherService.findByTeaid(name);
        if (teacher != null) {
            logger.info("---------------name:{}", teacher.getName());
            if (password.equals(teacher.getPassword())) {
                logger.info("-----------密码正确");
                return "teacher/index";
            }
        }
        User user = userService.findByStuid(name);
        if (user != null) {
            logger.info("---------------name:{}", user.getName());
            return "student/index";
        }
        modelMap.addAttribute("iserro", true);
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(ExamtestsystemApplication.class, args);
    }
}
