package com.henu.examtestsystem;

import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/403")
    public String error() {
        return "403";
    }

    @RequestMapping(value = {"/", "/login"})
    public String home() {
        return "index";
    }

    @RequestMapping(value = {"/togo"})
    public String go(String name, String password, ModelMap modelMap) {
        User user = userRepository.findByIdnumber(name);
        boolean f = false;
        if (user == null) {
            f = true;
        }
        if (!f) {
            boolean is = MD5Service.checkpassword(password, user.getPassword());
            if (!is) {

            }
        }

        return "redirect:/";
    }


}
