package com.henu.examtestsystem.student.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.teacher.controller.TeacherController;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String home() {
        return "/student/index";
    }

    /**
     * 跳转到考试列表界面
     */
    @RequestMapping("/exam_list")
    public String red(Model model, HttpSession session) {
        boolean f = false;
        User user = (User) session.getAttribute("user");
        List<Exam> list = user.getExams();
        Exam exam = null;
        for (Exam e : list
                ) {
            if (e.getExamState()==Exam.ExamState.now) {
                f = true;
                exam=e;break;
            }
        }
        model.addAttribute("if_paper", f);
        model.addAttribute("exam",exam);
        return "/student/examList";
    }


    /**
     * 跳转到提交列表界面
     */
    @RequestMapping("/sub")
    public String reds(ModelMap model, HttpSession session) {
        List<Pair<String, String>> files = new LinkedList<Pair<String, String>>();
        User user = (User) session.getAttribute("user");
        List<Exam> list = user.getExams();
        String path = null;
        for (Exam e : list
                ) {
            if (e.getExamState().equals(Exam.ExamState.now)) {
                path = e.getPath();
                break;
            }
        }
        path = '.' + path + user.getName();
        File file = new File(path);
        File[] tempList = file.listFiles();
        if(tempList!=null)
        for (File f: tempList
             ) {
            if (f.isFile())
            {
                files.add(new Pair<String ,String>(f.getName().toString(),f.length()+"字节"));
            }
        }
        model.addAttribute("files",files);
        return "/student/subList";
    }

    /**
     * 实现文件上传
     * 每个学生上传的答案会存放在以该学生名字命名的文件夹中
     */
    @RequestMapping(value = "/submit",method = POST)
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             HttpSession session) {
        if (file.isEmpty()) {
            return "redirect:/student/sub";
        }

        User user = (User) session.getAttribute("user");
        List<Exam> list = user.getExams();
        String name = file.getOriginalFilename();
        String path = null;
        for (Exam exam : list
                ) {
            if (exam.getExamState() == Exam.ExamState.now) {
                path = exam.getPath();
            }
        }
        if (path == null) return "redirect:/student/sub";
        path = path + user.getName() +"/";
        File file1 = new File("."+path);

        if(!file1.exists()) {file1.mkdirs();}
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File('.'+path+user.getName()+'_'+ name)));
            stream.write(bytes);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/student/sub";
    }
    @RequestMapping(value = "get_mess")
    public @ResponseBody String get_mess( HttpSession session){
        User user=(User)session.getAttribute("user");
        Long id = new Long(0);
        for (Exam e: user.getExams()
             ) {
            if(e.getExamState()==Exam.ExamState.now){
                id = e.getId();
            }
        }
        List<String> list =null;
        if(TeacherController.mess_map!=null&&TeacherController.mess_map.containsKey(id))
        {
            list = TeacherController.mess_map.get(id);
        }
        String mess="";
        if(list!=null)
        {
            for (String str:list
                 ) {
                mess+=str+"\n";
            }
        }
        return mess;
    }

}
