package com.henu.examtestsystem.teacher.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.ExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Value("${uploadpath}")
    private String exams_path;

    @Autowired
    ExamRepository examRepository;

    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @RequestMapping(value = "/")
    public String home() {
        return "/teacher/index";
    }

    @RequestMapping(value = "/exam-before")
    public ModelAndView beforeexam(ModelMap modelMap) {
        ModelAndView mv = new ModelAndView();
        try {
            List<Exam> exams = examRepository.findAll();
            modelMap.addAttribute("exams", exams);
        } catch (Exception e) {
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("msg", "查找所有考试失败");
        }
        mv.setViewName("/teacher/exam-before");
        return mv;
    }

    @RequestMapping(value = "/add-exam")
    public ModelAndView add_exam(String ename, String starttime, String eautostart,
                                 HttpServletRequest request, ModelMap modelMap,
                                 HttpSession session) {
        ModelAndView mv = new ModelAndView();
        boolean f = false;

        if (ename == null || ename.length() <= 0 ||
                starttime.length() <= 0 || starttime == null) {
            f = true;
        }

        try {

            logger.info("ename:{},starttime:{}", ename, starttime);
            String path = exams_path + ename;
            String pathAns = path + "/答案";
            java.io.File file = new java.io.File(path);
            java.io.File fileAns = new java.io.File(pathAns);
            logger.info("-------====realPath:{},path:{}",
                    path, "sda");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!fileAns.exists()) {
                fileAns.mkdirs();
            }
            Exam exam = new Exam();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                exam.setCreateUser(user.getName());
            }
            Date date = sf.parse(starttime);
            exam.setSubject(ename);
            exam.setStart_date(date);
            exam.setHasClean(false);
            exam.setHasStore(false);
            exam.setAutostart(false);
            exam.setPaper_path(pathAns);
            exam.setPath(path);
            exam.setExamState(Exam.ExamState.future);
            if (eautostart != null) {
                exam.setAutostart(true);
            }
            examRepository.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
            f = true;
        }
        if (f) {
            modelMap.addAttribute("error", f);
            modelMap.addAttribute("msg", "添加失败，考试名称不正确或已存在或者日期不正确");
            logger.info("f:{},msg:{}", f, "失败");
            mv.setViewName("/teacher/exam-before");
        } else {
            mv.setViewName("redirect:exam-before");
        }
        return mv;
    }


    @RequestMapping(value = "/editExam/{id}")
    public ModelAndView editExam(@PathVariable(value = "id") Long id, ModelMap modelMap) {
        ModelAndView mv = new ModelAndView();
        Exam exam = examRepository.findOne(id);
        modelMap.addAttribute("exam", exam);
        mv.setViewName("/teacher/editExam");
        return mv;
    }

    @RequestMapping(value = "/editExam/{id}/edit")
    public ModelAndView editExamId(@PathVariable(value = "id") Long id,
                                   ModelMap modelMap, String ename,
                                   String starttime, String eautostart) {
        ModelAndView mv = new ModelAndView();
        Exam exam = examRepository.findOne(id);

        Date date = null;
        try {
            exam.setId(id);
            date = sf.parse(starttime);
            exam.setSubject(ename);
            exam.setStart_date(date);
            if (eautostart != null) {
                exam.setAutostart(true);
            }
            examRepository.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("redirect:/teacher/editExam/" + id);
        return mv;
    }


}