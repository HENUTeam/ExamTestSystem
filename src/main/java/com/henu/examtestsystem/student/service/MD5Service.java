package com.henu.examtestsystem.student.service;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class MD5Service {
    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     */
    public static String EncoderByMd5(String str) {
        //确定计算方法
        String newstr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException s) {
            s.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

    /**
     * 判断密码是否一致
     *
     * @param newpasswd 用户输入的密码
     * @param oldpasswd 数据库中存储的密码－－用户密码的摘要
     * @return
     */
    public static boolean checkpassword(String newpasswd, String oldpasswd) {
        if (EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
}
