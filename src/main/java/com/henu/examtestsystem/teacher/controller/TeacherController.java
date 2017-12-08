package com.henu.examtestsystem.teacher.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @RequestMapping(value = "/")
    public String home() {
        return "/teacher/index";
    }

    @RequestMapping(value = "/exam-before")
    public ModelAndView beforeexam(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/teacher/exam-before");
        return mv;
    }

    @RequestMapping(value = "add-exam")
    public ModelAndView add_exam(String ename, String starttime,
                                 HttpServletRequest request,  ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        boolean f= false;
        if(ename==null||ename.length()<=0||
                starttime.length()<=0||starttime==null){
            f = true;
        }
        try {

        }catch (Exception e){
            f=true;
        }
        if(f){
            modelMap.addAttribute("error", f);
            modelMap.addAttribute("msg","添加失败，考试名称不正确或已存在或者日期不正确");
            logger.info("f:{},msg:{}",f,"失败");
            mv.setViewName("/teacher/exam-before");
        } else{
            mv.setViewName("redirect:exam-before");
        }
        return mv;
    }

}
