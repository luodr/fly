package com.ldr.util;


 
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;



public class DateUtil {

  public static String  Format(long d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(d);
  }
  public static String  FormatYMDHm(long d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		return sdf.format(d);
  }
  public static String  FormatYMD(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(d);
  }
  public static String  FormatYMD(long d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(d);
  }
}