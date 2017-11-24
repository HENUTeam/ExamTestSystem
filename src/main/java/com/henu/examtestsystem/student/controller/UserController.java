package com.henu.examtestsystem.student.controller;

import com.henu.examtestsystem.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/exam_list")
    public String red() {
        return "/student/examList";
    }


}
