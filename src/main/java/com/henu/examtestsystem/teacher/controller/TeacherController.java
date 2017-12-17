package com.henu.examtestsystem.teacher.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.ExamRepository;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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

            logger.info("ename:{},starttime:{},eautostart:{}", ename, starttime, eautostart);
            String path = exams_path + ename + "/";
            String pathAns = path + "答案/";
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
            exam.setStartDate(date);
            exam.setHasClean(false);
            exam.setHasStore(false);
            exam.setAutoStart(false);
            exam.setPaperPath(path);
            exam.setPath(pathAns);
            exam.setExamState(Exam.ExamState.future);
            if (eautostart != null) {
                exam.setAutoStart(true);
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
            exam.setStartDate(date);
            if (eautostart != null) {
                exam.setAutoStart(true);
            }
            examRepository.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("redirect:/teacher/editExam/" + id);
        return mv;
    }


    @RequestMapping(value = "exam_upload/{id}")
    @ResponseBody
    public String examUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String msg = "";
        try {
            if (file == null || file.getBytes().length <= 0) {
                msg = "选中文件为空";
                return msg + "<a href=\"/teacher/editExam/" + id + "/edit\">考试编辑页面</a>";
            }
        } catch (IOException e) {
            e.printStackTrace();
            msg = "选中文件为空";
            return msg + "<a href=\"/teacher/editExam/" + id + "/edit\">考试编辑页面</a>";
        }
        Exam exam = examRepository.findOne(id);
        String path = exam.getPaperPath();
        File parent = new File(path);
        if (parent.isDirectory()) {
            File[] fileList = parent.listFiles();
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isFile()) {
                        logger.info("删除文件:{}", fileList[i].getName());
                        fileList[i].delete();
                    }
                }
            }
            File file1 = new File(path + "考试试卷" + file.getOriginalFilename());
            try {
                UploadController.upLoadFile(file1, file);
                exam.setHasPaper(true);
                examRepository.save(exam);
                msg = "上传成功";
            } catch (Exception e) {
                msg = "上传失败";
            }
        }
        return msg + "<a href=\"/teacher/editExam/" + id + "/edit\">考试编辑页面</a>";
    }

    @RequestMapping(value = "exam_download/{id}")
    @ResponseBody
    public String examUpload(@PathVariable Long id, HttpServletResponse response) {
        String msg = "";
        try {
            Exam exam = examRepository.findOne(id);
            String path = exam.getPaperPath();
            File parent = new File(path);
            if (parent.isDirectory()) {
                File[] fileList = parent.listFiles();
                if (fileList != null && fileList.length > 0) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            logger.info("下载文件:{}", fileList[i].getName());
                            UploadController.downLoad(path + fileList[i].getName(), response);
                            break;
                        }
                    }
                }
            }
            msg = "下载成功";
        } catch (Exception e) {
            msg = "下载失败";
        }
        return msg + "<a href=\"/teacher/editExam/" + id + "/edit\">考试编辑页面</a>";
    }

}
