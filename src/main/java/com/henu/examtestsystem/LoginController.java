package com.henu.examtestsystem;

import com.henu.examtestsystem.student.bean.Exam;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.spring4.view.AjaxThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String home(String key, Model model, String error) {
        logger.info("----}“{}", error);
        if (key != null && key.equals("error")) {
            model.addAttribute("error", true);
        } else if (key != null && key.equals("stu_error")) {
            model.addAttribute("stu_error", true);
        }
        return "index";
    }

    @RequestMapping(value = {"/togo"})
    public String go(String name, String password, HttpSession session) {
        User user = userRepository.findByIdnumber(name);

        boolean f = false;
        boolean flag = true;
        if (user == null) {
            f = true;
        }
        if (!f) {
            boolean is = MD5Service.checkpassword(password, user.getPassword());
            if (!is) {
                f = true;
                flag = false;
            } else if (user.getRole().equals(User.Role.student)) {
                List<Exam> list = user.getExams();
                for (Exam e : list) {
                    if (e.getExamState().equals(Exam.ExamState.now)) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (f) {
            //session.setAttribute("info_erro", f);
            return "redirect:/login?key=error";
        } else {
            session.setAttribute("user", user);
            logger.info(user.getPassword());
            if (user.getRole().equals(User.Role.student)) {
                if (flag) return "redirect:/login?key=stu_error";
                return "redirect:/student/";
            } else if (user.getRole().equals(User.Role.teacher)) {
                return "redirect:/teacher/";
            } else if (user.getRole().equals(User.Role.admin)) {
                return "redirect:/manager/";
            }
        }
        return "index";
    }


    @RequestMapping("/toEditself")
    public String toEdit(String oldpwd, String password, String newpwd,
                         HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        modelMap.addAttribute("user", user);
        boolean f = false;
        if (oldpwd != null && password != null && newpwd != null) {
            logger.info("password:{},newpwd{}", password.length(), newpwd.length());
            boolean a = MD5Service.checkpassword(oldpwd, user.getPassword());
            f = false;
            if (!a) {
                f = true;
                modelMap.addAttribute("msg", "输入的旧密码不正确");
            } else if (password.length() <= 0) {
                f = true;
                modelMap.addAttribute("msg", "请输入新的密码");
            } else if (!password.equals(newpwd)) {
                f = true;
                modelMap.addAttribute("msg", "两次输入密码不一致");
            }
            if (f) {
                modelMap.addAttribute("f", f);
                return "/editPwd";
            } else {
                user.setPassword(MD5Service.EncoderByMd5(password));
                try {
                    user = userRepository.save(user);
                    return "redirect:/logout";
                } catch (Exception e) {
                    modelMap.addAttribute("f", f);
                    modelMap.addAttribute("msg", "修改失败");
                    return "/editPwd";
                }
            }
        }
        return "/editPwd";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        String name = (String) request.getAttribute("name");
        ModelMap modelMap = new ModelMap();
        logger.info(name);
        modelMap.addAttribute("error", true);
        return ("index");
    }

}
