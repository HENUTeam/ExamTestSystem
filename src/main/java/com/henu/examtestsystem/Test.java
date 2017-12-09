package com.henu.examtestsystem;

import com.henu.examtestsystem.student.service.MD5Service;

public class Test {

    public static void main(String[] args) {
        String pwd = "12345";
        String md5 = "gnzLDuqKcGxMNKFokfhOew==";
        boolean is = MD5Service.checkpassword(pwd, md5);
        System.out.println(is);
    }

}
