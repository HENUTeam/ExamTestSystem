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


}
