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
        if (!upFile.getParentFile().exists()) {
            upFile.getParentFile().mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(upFile));
                stream.write(file.getBytes());
                stream.close();
                return "true";
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "false";
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "false";
            }
        } else {
            return "false";
        }

    }

    @RequestMapping("download")
    public String downLoad(HttpServletResponse response) {
        String filename = "2.jpg";
        String filePath = "./exams/Java考试/";
        File file = new File(filePath + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
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
