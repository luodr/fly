package com.ldr.util;


 
import java.security.MessageDigest;



public class MD5Util {


    private static final String slat = "&%1A2Asc*&%$$#@";

    /**
     * 原生的加密方法
     *
     * @param value 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String value) {
        try {
            value = value + slat;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(value.getBytes("UTF8"));
            byte message[] = messageDigest.digest();
            String result = "";
            for (int i = 0; i < message.length; i++) {
                result += Integer.toHexString((0x000000FF & message[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}