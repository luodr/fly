package com.ldr.util;


 
import java.security.MessageDigest;



public class MD5Util {


    private static final String slat = "&%1A2Asc*&%$$#@";

    /**
     * ԭ���ļ��ܷ���
     *
     * @param value ��Ҫ���ܵ��ַ���
     * @return ���ܺ���ַ���
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