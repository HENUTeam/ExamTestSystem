package com.henu.examtestsystem.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/teacher")
public class TecherController {

    @RequestMapping(value = "/")
    public String home() {
        return "/teacher/index";
    }


}
