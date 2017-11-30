package com.henu.examtestsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/403")
    public String error() {
        return "403";
    }

    @RequestMapping(value = {"/", "/login"})
    public String home() {
        return "index";
    }


}
