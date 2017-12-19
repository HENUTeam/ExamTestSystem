package com.henu.examtestsystem.teacher.controller;

import com.henu.examtestsystem.student.bean.Exam;
import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.ExamRepository;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import com.henu.examtestsystem.teacher.util.POIUtil;
import com.henu.examtestsystem.teacher.util.UploadUtil;
import com.henu.examtestsystem.student.repository.UserRepository;
import com.henu.examtestsystem.student.service.MD5Service;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
import java.util.*;

/**
 * @author 高逸博
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private UserRepository userRepository;

    public static Map<Long,List<String> > mess_map;
    private SimpleDateFormat sf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );

    @Value("${uploadPath}")
    private String exams_path;

    @Value("${zipPath}")
    private String zipPath;

    @Autowired
    private ExamRepository examRepository;

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

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

    @RequestMapping(value = "/push_mes")
    public String push_mes(ModelMap model,HttpSession session,String mess)
    {
        if(mess_map==null) mess_map=new HashMap<Long, List<String>>();
        User  user = (User)session.getAttribute("user");
        Long id=new Long(0);
        for (Exam e: user.getExams()
             ) {
            if (e.getExamState()==Exam.ExamState.now)
            {
                id=e.getId();
            }
        }
        if (mess_map.containsKey(id))
        {
            if(mess!=null&&!mess.equals(""))
            mess_map.get(id).add(mess);
        }else
        {
            List<String> list = new LinkedList<String>();
            if(mess!=null&&!mess.equals(""))
            list.add(mess);
            mess_map.put(id,list);
        }
        model.addAttribute("stus",mess_map.get(id));
        return "teacher/mid-stu-notify";
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
        return "redirect:/teacher/push_mes";
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
                UploadUtil.upLoadFile(file1, file);
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
                            UploadUtil.downLoad(path + fileList[i].getName(), response);
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

    @RequestMapping(value = "/editExam/{id}/edit/addStudent")
    public String addStudent(@PathVariable Long id, ModelMap modelMap,
                             String sno, String name) {

        Exam exam = examRepository.findOne(id);
        List<User> stus = exam.getUser();
        modelMap.addAttribute("stus", stus);
        modelMap.addAttribute("exam", exam);

        if (sno == null || sno.length() <= 0 || name.length() <= 0) {
            modelMap.addAttribute("info", "学号和姓名都不能为空");
        } else {
            try {
                addStudent(sno, name, id);
                modelMap.addAttribute("info", "添加成功！");
                logger.info("-------------sno:{},name:{},msg:添加成功", sno, name);
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.addAttribute("info", "添加失败！" + e.getMessage());
            }
        }
        return "teacher/addStudent";
    }

    @RequestMapping(value = "/editExam/{id}/edit/addMany")
    public String addStudentExcel(@PathVariable Long id, ModelMap modelMap,
                                  @RequestParam("file") MultipartFile file) {
        Exam exam = examRepository.findOne(id);
        List<User> stus = exam.getUser();
        modelMap.addAttribute("stus", stus);
        modelMap.addAttribute("exam", exam);
        try {
            List<String[]> users = POIUtil.readExcel(file);
            int success = 0;
            StringBuilder sbError = new StringBuilder();
            for (int i = 0; i < users.size(); i++) {
                String[] user = users.get(i);
                try {
                    addStudent(user[0], user[1], id);
                    success++;
                } catch (Exception e) {
                    sbError.append((i + 2) + ",");
                    e.printStackTrace();
                }
            }
            String s = String.format("共发现%d条数据,成功条数为：%d,不成功行数为：%s", users.size(), success, sbError);
            modelMap.addAttribute("msgExcel", s);
        } catch (Exception e) {
            modelMap.addAttribute("msgExcel", e.getMessage());
            e.printStackTrace();
        }
        return "teacher/addStudent";
    }


    /**
     * 根据sno，sname和考试的id来添加学生
     *
     * @param sno  学号
     * @param name 姓名
     * @param id   考试id
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public void addStudent(String sno, String name, Long id) throws Exception {
        Exam exam = examRepository.findOne(id);
        User user = userRepository.findByIdnumber(sno);
        List<Exam> exams = null;
        if (user == null) {
            user = new User();
            exams = new ArrayList<Exam>();
            user.setRole(User.Role.student);
            String s = "";
            if (sno.length() < 4) {
                s = "1234";
            } else {
                s = sno.substring(sno.length() - 4);
            }
            user.setIdnumber(sno);
            user.setPassword(MD5Service.EncoderByMd5(s));
            user.setName(name);
            user.setExams(exams);
            user.setSex(User.Sex.男);
        }
        exams = user.getExams();
        if (!exams.contains(exam)) {
            exams.add(exam);
        }
        user.setExams(exams);
        userRepository.save(user);
        List<User> users = exam.getUser();
        if (users == null) {
            users = new ArrayList<User>();
        }
        if (!users.contains(user)) {
            users.add(user);
        }
        exam.setUser(users);
        examRepository.save(exam);
    }


    @RequestMapping(value = "exam-after")
    public ModelAndView exam_after(ModelMap modelMap) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("teacher/exam-after");
        try {
            //查找所有的考试并传送到前台页面
            List<Exam> exams = examRepository.findAll();
            modelMap.addAttribute("exams", exams);
        } catch (Exception e) {
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("msg", "查找所有考试失败");
        }
        return mv;
    }

    @RequestMapping(value = "exam-after/zip/{id}")
    public String zipExam(@PathVariable Long id, HttpServletResponse response) {
        Exam exam = examRepository.findOne(id);
        String path = exam.getPaperPath();
        String[] names = path.split("/");
        String name = names[names.length - 1];
        String target = zipPath + name + ".zip";
        String source = path;
        try {
            File file = new File(target);
            if (file.exists()) {
                file.delete();
            }
            UploadUtil.compressedFile(source, zipPath);
            UploadUtil.downLoad(target, response);
            logger.info("==========target:{}", target);
            exam.setHasStore(true);
            exam.setHasClean(true);
            examRepository.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/teacher/exam-after";
    }

    @RequestMapping(value = "/editExam/start/{id}")
    public String startExam(@PathVariable Long id) {
        Exam exam = examRepository.findOne(id);
        if (exam.getExamState() == Exam.ExamState.future) {
            exam.setExamState(Exam.ExamState.now);
        } else {
            exam.setExamState(Exam.ExamState.end);
        }
        examRepository.save(exam);
        return "redirect:/teacher/editExam/" + id;
    }

}
