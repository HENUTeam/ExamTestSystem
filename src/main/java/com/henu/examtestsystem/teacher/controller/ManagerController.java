package com.henu.examtestsystem.teacher.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.ExamRepository;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExamRepository examRepository;

    Logger logger = LoggerFactory.getLogger(ManagerController.class);


    @RequestMapping(value = "/")
    public String home() {
        return "/manager/index";
    }


    @RequestMapping("/toAdd")
    public String toAdd(ModelMap modelMap) {
        String s = "";
        User user = new User();
        modelMap.addAttribute("user", user);
        return "/manager/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user, ModelMap modelMap, String admin) {
        User user1 = userRepository.findOne(user.getId());
        try {
            user1.setRole(User.Role.teacher);
            if (admin != null) {
                user1.setRole(User.Role.admin);
            }
            user1.setName(user.getName());
            user1.setIdnumber(user.getIdnumber());
            userRepository.save(user1);
        } catch (Exception e) {
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("user", user);
            return "/manager/userAdd";
        }

        return "forward:teacheradd";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user = userRepository.findOne(id);
        boolean f = false;
        if (user.getRole() == User.Role.admin) {
            f = true;
        }
        model.addAttribute("user", user);
        model.addAttribute("f", f);
        return "/manager/userAdd";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.delete(id);
        return "forward:teacheradd";
    }

    @RequestMapping("/teacheradd")
    public String teacheradd(ModelMap modelMap) {
        List<User> users = userRepository.findAdminOrTeacher();
        modelMap.addAttribute("users", users);
        return "/manager/teacheradd";
    }

    @RequestMapping("/toteaAdd")
    public String toTeaAdd(User user, String admin, ModelMap modelMap) {
        user.setRole(User.Role.teacher);
        user.setSex(User.Sex.男);
        if (admin != null) {
            user.setRole(User.Role.admin);
        }
        boolean f = false;
        if (user.getName().length() <= 0 || user.getIdnumber().length() <= 0 || user.getPassword().length() <= 0) {
            f = true;
        } else {
            user.setPassword(MD5Service.EncoderByMd5(user.getPassword()));
            try {
                userRepository.save(user);
            } catch (Exception e) {
                f = true;
                e.printStackTrace();
            }
        }
        modelMap.addAttribute("f", f);
        logger.info("------error:{}", f);
        return "forward:teacheradd";
    }

    @RequestMapping("/delectexam")
    public String delectexam(ModelMap modelMap) {
        List<Exam> exams = examRepository.findAll();
        List<Exam> show = new ArrayList<Exam>();
        for(int i=0;i<exams.size(); i++){
            Exam exam = exams.get(i);
            if(exam.isShow()){
                show.add(exam);
            }
        }
        modelMap.addAttribute("exams", show);
        return "/manager/delectexam";
    }

    @RequestMapping("/delectexam/{id}")
    public String delectexamByID(@PathVariable Long id) {
        try {
            Exam exam = examRepository.findOne(id);
            exam.setShow(false);
            examRepository.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/delectexam";
    }

    public boolean deleteFolder(String url) {
        File file = new File(url);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String root = files[i].getAbsolutePath();//得到子文件或文件夹的绝对路径
                //System.out.println(root);
                deleteFolder(root);
            }
            file.delete();
            return true;
        }
    }

    @RequestMapping("/sysconfig")
    public String sysconfig() {
        return "/manager/sysconfig";
    }
}
