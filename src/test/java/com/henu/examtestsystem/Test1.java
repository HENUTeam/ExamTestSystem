package com.henu.examtestsystem;

import com.henu.examtestsystem.student.bean.User;
import com.henu.examtestsystem.student.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

    @Test
    public void test1() {
        String[] exurls = "/login,/, /css/**, /images/**, /js/**, /exams/**,/files/**,/togo".split(",");
        System.out.println(exurls);
    }
    @Autowired
    UserRepository userRepository;

    @Test
    public void testfile() {
        File file = new File("./exams/testadsdsad/");
        if (!file.exists()) {
            file.mkdir();
            System.out.println("创建成功");
        }
        file.mkdir();
    }
}
