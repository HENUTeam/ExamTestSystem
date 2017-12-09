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

    @RequestMapping(value = "stu_info")
    public String stuInfo()
    {
        return "/teacher/mid-stu-info";
    }
    @RequestMapping(value = "ip")
    public String ip(){
        return "/teacher/mid-stu-ip";
    }
    @RequestMapping(value = "notf")
    public String noty(){
        return "/teacher/mid-stu-notify";
    }

}
