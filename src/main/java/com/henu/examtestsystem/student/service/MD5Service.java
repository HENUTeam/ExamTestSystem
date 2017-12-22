package com.henu.examtestsystem.student.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
            // 计算md5函数
            md5.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            newstr = new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException s) {
            s.printStackTrace();
        } catch (Exception  e) {
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
