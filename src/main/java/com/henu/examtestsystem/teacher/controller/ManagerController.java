package com.henu.examtestsystem.teacher.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(ManagerController.class);


    @RequestMapping(value = "/")
    public String home() {
        return "/manager/index";
    }


    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "manager/showUser";
    }

    @RequestMapping("/toAdd")
    public String toAdd(ModelMap modelMap) {
        String s = "";
        User user = new User();
        modelMap.addAttribute("user", user);
        return "/manager/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user, ModelMap modelMap) {

        try {
            user.setCreatedate(new Date());
            String pwd = user.getPassword();
            logger.info("-----------md5之前：{}", pwd);
            String pwdmd5 = MD5Service.EncoderByMd5(pwd);
            logger.info("-----------md5之前：{}", pwdmd5);
            user.setPassword(pwdmd5);
            userRepository.save(user);
        } catch (Exception e) {
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("user", user);
            return "/manager/userAdd";
        }

        return "redirect:list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "/manager/userAdd";
    }


    @RequestMapping("/edit")
    public String edit(User user) {
        //System.out.println("--------"+user.getName());
        userRepository.save(user);
        return "redirect:list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.delete(id);
        return "redirect:list";
    }

    @RequestMapping("/test")
    public String test() {
        return "manager/layout";
    }

    @RequestMapping("/teacheradd")
    public String teacheradd(ModelMap modelMap, boolean error) {
        List<User> users = userRepository.findAdminOrTeacher();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("error", error);
        return "/manager/teacheradd";
    }

    @RequestMapping("/toteaAdd")
    public String toTeaAdd(User user, String admin) {
        user.setRole(User.Role.teacher);
        user.setSex(User.Sex.男);
        if (admin != null) {
            user.setRole(User.Role.admin);
        }
        boolean f = false;
        if (user.getName().length() <= 0 || user.getIdnumber().length() <= 0 || user.getPassword().length() <= 0) {
            f = true;
        } else {
            user.setCreatedate(new Date());
            user.setPassword(MD5Service.EncoderByMd5(user.getPassword()));
            try {
                userRepository.save(user);
            } catch (Exception e) {
                f = true;
                e.printStackTrace();
            }
        }
        logger.info("------error:{}", f);
        return "redirect:teacheradd?error=" + f;
    }

    @RequestMapping("/delectexam")
    public String delectexam() {
        return "/manager/delectexam";
    }

    @RequestMapping("/sysconfig")
    public String sysconfig() {
        return "/manager/sysconfig";
    }
}
