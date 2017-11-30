package com.henu.examtestsystem.student.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String home() {
        return "/student/index";
    }

    /**跳转到考试列表界面
     *
     * */
    @RequestMapping("/exam_list")
    public String red() {
        return "/student/examList";
    }


    /**跳转到提交列表界面
     *
     * */
    @RequestMapping("/sub_list")
    public String reds(){
        return "/student/subList";
    }
    /**
     * 实现文件上传
     * 未测试
     * */
    @RequestMapping("/submit")
    @ResponseBody
    public String fileUpload(@RequestParam("paper") MultipartFile file,HttpSession session){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        User user = (User)session.getAttribute("user");
        List<Exam> list =user.getExams();
        String path =null;
        for (Exam exam: list
             ) {
            if(exam.getExamState()==Exam.ExamState.now)
            {
                path =exam.getPath();
            }
        }
        if(path==null) return "false";

        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {

            e.printStackTrace();
            return "false";
        } catch (IOException e) {

            e.printStackTrace();
            return "false";
        }
    }



}
