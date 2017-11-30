package com.henu.examtestsystem.student.controller;

import com.henu.examtestsystem.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String home() {
        return "/student/index";
    }

    @RequestMapping("/exam_list")
    public String red() {
        return "/student/examList";
    }


}
