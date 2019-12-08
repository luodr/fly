package com.ldr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.ldr.bean.User;



public class UserDao {
	private static UserDao userDao = null;
	   private UserDao() {
	   }
	  
	 public static UserDao getInstance() {
	      if (userDao == null) {
	    	 userDao = new UserDao();
	     }
	       return userDao;
	   }
	    
	public  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/shop56?useUnicode=true&characterEncoding=UTF-8";
			Connection dbCon=DriverManager.getConnection(url,"root","123456");
			return dbCon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public  boolean registUser(String userName,String password,int type){
		ResultSet rs;
		try{
			Connection con=this.getConnection();
			String sql ="select * from user where user = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,userName);
			rs = pst.executeQuery();
		      //已被注册
			if(rs.next()){
				return false;
			}else{ //注册
				sql ="insert into user(user,password,type) values(?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setString(1,userName);
				pst.setString(2, password);
				pst.setInt(3, type);
			   if(pst.executeUpdate()>1){
				return false;
		     	}else{
				return true;
			    }
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
}
	
	public User findByUser(String user){
		ResultSet rs;
		try{
			Connection con=this.getConnection();
			String sql ="select * from user where user = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,user);		
			rs = pst.executeQuery();
		      //已被注册
			while (rs.next()){
				User u=new User();
				u.setAddress(rs.getString("address"));
				u.setId(rs.getInt("id"));
				u.setMail(rs.getString("mail"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setUser(rs.getString("user"));
				return u;
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	
}
