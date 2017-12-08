package com.henu.examtestsystem;

import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

    @Test
    public void test1(){
        String []exurls = "/login,/, /css/**, /images/**, /js/**, /exams/**,/files/**,/togo".split(",");
        System.out.println(exurls);
    }

    @Test
    public void testfile(){
        File file = new File("/exams/testadsdsad/");
        if(!file.exists()){

        }
        file.mkdir();
    }
}
