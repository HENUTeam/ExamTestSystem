package com.henu.examtestsystem;

import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String home(HttpSession session) {
        return "index";
    }

    @RequestMapping(value = {"/togo"})
    public String go(String name, String password, HttpSession session) {
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
            session.setAttribute("info_erro", f);
            return "redirect:/";
        } else {
            session.setAttribute("user", user);
            logger.info(user.getPassword());
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

    @RequestMapping(value = {"/resetpwd"})
    public String home(String oldpwd, String password, String newpwd, HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        boolean a = MD5Service.checkpassword(oldpwd, user.getPassword());
        boolean f = false;
        if (!a) {
            f = true;
            modelMap.addAttribute("msg", "输入的旧密码不正确");
        } else if (!password.equals(newpwd)) {
            f = true;
            modelMap.addAttribute("msg", "两次输入密码不一致");
        }
        if (f) {
            modelMap.addAttribute("error", true);
            return "/manager/editPwd";
        }
        return "redirect:/manager/list";
    }

    @RequestMapping("/toEditself")
    public String toEdit(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        logger.info(user.getPassword());
        return "/manager/editPwd";
    }



}
