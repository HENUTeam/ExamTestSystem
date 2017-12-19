package com.henu.examtestsystem.teacher.util;

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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class UploadUtil {

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
            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(filePath, "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
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


    /**
     * @param resourcesPath 源文件/文件夹
     * @param targetPath    目的压缩文件保存路径
     * @return void
     * @throws Exception
     * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip
     */
    public static void compressedFile(String resourcesPath, String targetPath) throws Exception {
        //源文件
        File resourcesFile = new File(resourcesPath);
        //目的
        File targetFile = new File(targetPath);
        //如果目的路径不存在，则新建
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //目的压缩文件名
        String targetName = resourcesFile.getName() + ".zip";
        FileOutputStream outputStream = new FileOutputStream(targetPath + "/" + targetName);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
        createCompressedFile(out, resourcesFile, "");
        out.close();
    }

    /**
     * @param out  输出流
     * @param file 目标文件
     * @return void
     * @throws Exception
     * @desc 生成压缩文件。
     * 如果是文件夹，则使用递归，进行文件遍历、压缩
     * 如果是文件，直接压缩
     */
    public static void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
        //如果当前的是文件夹，则进行进一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));
            dir = dir.length() == 0 ? "" : dir + "/";
            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                //递归处理
                createCompressedFile(out, files[i], dir + files[i].getName());
            }
        } else {   //当前的是文件，打包处理
            //文件输入流
            FileInputStream fis = new FileInputStream(file);

            out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j = 0;
            byte[] buffer = new byte[1024];
            while ((j = fis.read(buffer)) > 0) {
                out.write(buffer, 0, j);
            }
            //关闭输入流
            fis.close();
        }
    }


}
