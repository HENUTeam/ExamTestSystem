package com.henu.examtestsystem.teacher.controller;


import com.henu.examtestsystem.ExamtestsystemApplication;
import com.henu.examtestsystem.teacher.bean.Teacher;
import com.henu.examtestsystem.teacher.bean.User;
import com.henu.examtestsystem.teacher.service.TeacherService;
import com.henu.examtestsystem.teacher.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {


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
    public String login(ModelMap modelMap, String name, String password,
                        HttpSession session) {

        logger.info("---------name:{},password:{}", name, password);
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(name);
        if (!isNum.matches()) {
            modelMap.addAttribute("iserro", true);
            return "index";
        }
        Teacher teacher = teacherService.findByTeaid(name);
        if (teacher != null) {
            logger.info("---------------name:{}", teacher.getName());
            if (password.equals(teacher.getPassword())) {
                logger.info("-----------密码正确");
                session.setAttribute("user", teacher);
                return "redirect:/teacher";
            }
        }
        User user = userService.findByStuid(name);
        if (user != null) {
            logger.info("---------------name:{}", user.getName());
            if (password.equals(user.getPassword())) {
                session.setAttribute("user", user);
                return "redirect:/student";
            }
        }
        modelMap.addAttribute("iserro", true);
        return "index";
    }

    @RequestMapping(value = "/student")
    public String goStudent() {
        return "student/index";
    }

    @RequestMapping(value = "/teacher")
    public String goTeacher() {
        return "teacher/index";
    }

    @RequestMapping("/")
    public String login() {
        return "index";
    }

}
