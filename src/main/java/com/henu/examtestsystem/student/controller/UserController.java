package com.henu.examtestsystem.student.controller;

import com.henu.examtestsystem.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/exam_list")
    public String red() {
        return "/student/examList";
    }


}
