package com.henu.examtestsystem.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class UploadController {

    @RequestMapping(value = "/file")
    public String fileTest() {
        return "testUpload";
    }

    @RequestMapping(value = "/testUpload")
    @ResponseBody
    public String testUploadFile(HttpServletRequest req, @RequestParam("file") MultipartFile file) throws IOException {
        File upFile = new File("./exams/Java考试/" + file.getOriginalFilename());
        return upLoadFile(upFile, file);
    }

    public static String upLoadFile(File upFile, MultipartFile file) throws IOException {
        if (!upFile.getParentFile().exists()) {
            upFile.getParentFile().mkdirs();
        }
        if (!file.isEmpty()) {
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(upFile));
            stream.write(file.getBytes());
            stream.close();
            return "上传成功";
        } else {
            return "选中文件为空";
        }
    }


    public static String downLoad(String filePath, HttpServletResponse response) throws IOException {

        File file = new File(filePath);
        //判断文件父目录是否存在
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.setHeader("Content-Type", "text/html;charset=UTF-8");

            byte[] buffer = new byte[1024];
            //文件输入流
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            //输出流
            OutputStream os = null;
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


}
