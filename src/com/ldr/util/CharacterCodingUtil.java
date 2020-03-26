package com.ldr.util;

import java.io.UnsupportedEncodingException;

/**
 * �ж��ַ�����
 *
 * @author guyinyihun
 */
public class CharacterCodingUtil {
 
 
    private final static String ENCODE = "GBK";
 
    /**
     * �ж��Ƿ�ΪISO-8859-1
     *
     * @return
     */
    public static boolean checkISO(String str) {
        boolean flag = java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(str);
        return flag;
    }
 
    /**
     * �ж��Ƿ�ΪUTF-8
     *
     * @return
     */
    public static boolean checkUTF(String str) {
 
        boolean flag = java.nio.charset.Charset.forName("UTF-8").newEncoder().canEncode(str);
        return flag;
    }
 
    public static boolean checkUnicode(String str) {
 
        boolean flag = java.nio.charset.Charset.forName("unicode").newEncoder().canEncode(str);
        return flag;
    }
 
    /**
     * <p>
     * Title: getEncoding
     * </p>
     * <p>
     * Description: �ж��ַ�����
     * </p>
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = null;
//        try {
//            if (str.equals(new String(str.getBytes(encode), encode))) {
//                String s = encode;
//                return s;
//            }
//        } catch (Exception exception) {
//        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
 
    /**
     * <p>
     * Title: isoToutf8
     * </p>
     * <p>
     * Description: ISO-8859-1 ���� ת UTF-8
     * </p>
     *
     * @param str
     * @return
     */
    public static String isoToutf8(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
 
    /**
     * <p>
     * Title: utf8Toiso
     * </p>
     * <p>
     * Description: UTF-8 ���� ת ISO-8859-1
     * </p>
     *
     * @param str
     * @return
     */
    public static String utf8Toiso(String str) {
        try {
            return new String(str.getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
 
    /**
     * <p>Title: unicodeToCn</p>
     * <p>Description: unicode ת ����</p>
     *
     * @param unicode
     * @return
     */
    public static String unicodeToCn(String unicode) {
        /** �� \ u �ָ��Ϊjavaע��Ҳ��ʶ��unicode������м����һ���ո� */
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // ����unicode�ַ����� \ u ��ͷ����˷ָ���ĵ�һ���ַ���""��
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
 
 
    /**
     * <p>Title: cnToUnicode</p>
     * <p>Description: ����ת unicode</p>
     *
     * @param cn
     * @return
     */
    public static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }
 
    /**
     * URL ����
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 ����04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * URL ת��
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 ����04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
 
}