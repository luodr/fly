package com.ldr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public class ConnectManager {
	private static String Driver;
	private static String url;
	private static String database;
	private static String user;
	private static String password;
	private static String port;
	static{
		
		try{
			Properties pro = new Properties();
	
//			System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"database.properties"+"…µ±∆∞—");
//			FileInputStream in = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/database.properties");
		
//			pro.load(in);
//			Driver=(String)pro.get("Driver");
//			url=(String)pro.get("url");
//			database=(String)pro.get("database");
//			user=(String)pro.get("user");
//			password=(String)pro.get("password");
//			port=(String)pro.get("port");
//			in.close();
			
		        	Driver="com.mysql.jdbc.Driver";
					url="jdbc:mysql://localhost";
					port="3306";
					database="blog";
					user="root";
					password="123456";

		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("properties º”‘ÿ ß∞‹");
		}
	}
	public  static Connection getConnection(){
		try {
			Class.forName(Driver);
			String u=url+":"+port+"/"+database+"?useUnicode=true&characterEncoding=UTF-8";
			Connection dbCon=DriverManager.getConnection(u,user,password);
			return dbCon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
