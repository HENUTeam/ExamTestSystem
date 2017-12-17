package com.henu.examtestsystem.teacher.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.ExamRepository;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

   // @Value("${filespath}")
    //private String filePath;
    @Autowired
    private UserRepository userRepository;

    SimpleDateFormat sf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );

    @Value("${uploadpath}")
    private String exams_path;

    @Autowired
    private ExamRepository examRepository;

    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @RequestMapping(value = "/")
    public String home() {
        return "/teacher/index";
    }

    @RequestMapping(value = "/exam-before")
    public ModelAndView beforeexam(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        try {
            List<Exam> exams = examRepository.findAll();
            modelMap.addAttribute("exams", exams);
        }catch (Exception e){
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("msg","查找所有考试失败");
        }
        mv.setViewName("/teacher/exam-before");
        return mv;
    }

    @RequestMapping(value = "add-exam")
    public ModelAndView add_exam(String ename, String starttime, String eautostart,
                                 HttpServletRequest request, ModelMap modelMap,
                                 HttpSession session){
        ModelAndView mv = new ModelAndView();
        boolean f= false;

        if(ename==null||ename.length()<=0||
                starttime.length()<=0||starttime==null){
            f = true;
        }

        try {

            logger.info("ename:{},starttime:{}", ename, starttime);
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

    /***
     * 考中增加学生
     * @param sno
     * @param sname
     * @param password
     * @param session
     */
    @RequestMapping(value = "/addStu")
    public String addStu(String sno,String sname,String password,HttpSession session,ModelMap modelMap)
    {
        User user = new User();
        user.setIdnumber(sno);
        if(userRepository.findByIdnumber(sno)!=null)
        {
            modelMap.addAttribute("error",true);
            return  "/teacher/mid-stu-info";
        }
        user.setName(sname);
        user.setPassword(MD5Service.EncoderByMd5(password));
        User tea = (User) session.getAttribute("user");
        List<Exam> exa = tea.getExams();
        user.setRole(User.Role.student);
        if(exa!=null)
        for (Exam e:exa) {
            if(e.getExamState().equals(Exam.ExamState.now))
            {
                List<Exam> exams = new LinkedList<Exam>();
                exams.add(e);
                user.setExams(exams);
                e.getUser().add(user);
                examRepository.save(e);
            }
        }

        userRepository.save(user);
        modelMap.addAttribute("error",false);
        return  "/teacher/mid-stu-info";
    }

    /***
     * 考中查询学生信息
     * @param model
     * @param idNumber
     * @param sname
     * @return
     */
    @RequestMapping(value = "/findStu")
    public String findStu(Model model,String idNumber,String sname)
    {
        List<User> stus = null;
        if(!idNumber.equals("") && !sname.equals(""))
        {
            stus = userRepository.findByIdnumberAndName(idNumber,sname);
        }else if(!idNumber.equals(""))
        {
            stus = new LinkedList<User>();
            User user = userRepository.findByIdnumber(idNumber);
            if(user!=null)
            stus.add(user);
        }else if(!sname.equals(""))
        {
            stus = userRepository.findByName(sname);
        }
        model.addAttribute("stus",stus);
        return "teacher/mid-stu-info";
    }

    @RequestMapping(value = "/findStuLogin")
    public String findStuLogin(ModelMap model,String idNumber,String sname)
    {
        System.out.println(idNumber==null+"wwwwwwwwwwwwwwwww");
        System.out.println(sname+"wwwwwwwwwwwwwwwww");
        List<User> stus = null;
        if(!idNumber.equals("") && !sname.equals(""))
        {
            stus = userRepository.findByIdnumberAndNameAndIp(idNumber,sname);
        }else if(!idNumber.equals(""))
        {
            stus = userRepository.findByIdnumberAndIp(idNumber);
        }else if(!sname.equals(""))
        {
            stus = userRepository.findByNameAndIp(sname);
        }
        System.out.println(stus.size()+"wwwwwwwwwwwwwwwww");
        model.addAttribute("stus",stus);
        return "teacher/mid-stu-ip";
    }

    @RequestMapping(value = "findStuLoginIp")
    public String findStuLoginIp(ModelMap model,String ip)
    {
        List<User> stus =userRepository.findByIp(ip);
        model.addAttribute("stus",stus);
        return "teacher/mid-stu-ip";
    }

    @RequestMapping(value = "/exitip/{id}")
    public String exitip(@PathVariable(value = "id") String idNumber) {
        User user = userRepository.findByIdnumber(idNumber);
        user.setIp(null);
        userRepository.save(user);
        return "teacher/mid-stu-ip";
    }
    /***
     * 考中学生信息查看
     */
    @RequestMapping(value = "/info")
    public String stu_info()
    {
        return "/teacher/mid-stu-info";
    }
    /***
     * 考中接触锁定ip
     */
    @RequestMapping(value = "/ip")
    public String stu_ip()
    {
        return "/teacher/mid-stu-ip";
    }

    /***
     * 考中编辑考试信息
     * @return
     */
    @RequestMapping(value = "/notf")
    public String stu_notf()
    {
        return "/teacher/mid-stu-notify";
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
        if (file == null) {
            return "选中文件为空";
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
            File file1 = new File(path + file.getOriginalFilename() + "考试试卷");
            try {
                UploadController.upLoadFile(file1, file);
                exam.setHasPaper(true);
                examRepository.save(exam);
                return "上传成功";
            } catch (Exception e) {
                return "上传失败";
            }
        }
        return "上传失败";
    }


}
