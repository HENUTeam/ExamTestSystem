package com.henu.examtestsystem;

import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

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
    public String go(String name, String password, ModelMap modelMap, HttpSession session) {
        User user = userRepository.findByIdnumber(name);
        boolean f = false;
        if (user == null) {
            f = true;
        }
        if (!f) {
            boolean is = MD5Service.checkpassword(password, user.getPassword());
            if (!is) {
                f = true;
            }
        }
        if (f) {
            modelMap.addAttribute("info_erro", f);
            return "redirect:/";
        } else {
            session.setAttribute("user", user);
            if (user.getRole().equals(User.Role.student)) {
                return "redirect:/student/";
            } else if (user.getRole().equals(User.Role.teacher)) {
                return "redirect:/teacher/";
            } else if (user.getRole().equals(User.Role.admin)) {
                return "redirect:/manager/";
            }
            return "redirect:/manager/list";
        }
        //return "redirect:/";
    }


}
