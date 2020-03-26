package com.ldr.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;


import com.ldr.bean.User;

import com.ldr.util.ConnectManager;;

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
	    

	public  boolean registUser(User user){
		ResultSet rs;
		try{
			Connection con=ConnectManager.getConnection();
			String sql ="select * from user where user = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,user.getUser());
			rs = pst.executeQuery();
		      //已被注册
			if(rs.next()){
				return false;
			}else{ //注册
				sql ="insert into user(user,password,type,name,regTime) values(?,?,?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setString(1,user.getUser());
				pst.setString(2, user.getPassword());
				pst.setInt(3, user.getType());
				pst.setString(4, user.getName());
				pst.setDate(5, user.getRegTime());
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
public boolean verifyName(String name){
	ResultSet rs;
	try{
		Connection con=ConnectManager.getConnection();
		String sql ="select * from user where name like ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, name);
		rs = pst.executeQuery();
	      
		if(rs.next()){
			return false;
		}
	}
		catch (Exception e) {
		}
	return true;
}

public boolean verifyUser(String user){
	ResultSet rs;
	try{
		Connection con=ConnectManager.getConnection();
		String sql ="select * from user where user like ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, user);
		rs = pst.executeQuery();
	      
		if(rs.next()){
			return false;
		}
	}
		catch (Exception e) {
		}
	return true;
}
	public User findByUser(String user){
		ResultSet rs;
		try{
			Connection con=ConnectManager.getConnection();
			String sql ="select * from user where user = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,user);		
			rs = pst.executeQuery();
		      //已被注册
			while (rs.next()){
				User u=new User();
			
				u.setId(rs.getInt("id"));
			
				
				u.setPassword(rs.getString("password"));
			   u.setSex(rs.getString("sex"));
			   u.setType(rs.getInt("type"));
			   u.setName(rs.getString("name"));
				u.setUser(rs.getString("user"));
			  u.setAddress(rs.getString("address"));
			  u.setSignature(rs.getString("signature"));
			   u.setRegTime(rs.getDate("regTime"));
			   u.setImg(rs.getString("img"));
		//	   u.setUser(rs.getString("user"));
			   u.setMail(rs.getString("mail"));
				return u;
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public User findByName(String name){
		ResultSet rs;
		try{
			Connection con=ConnectManager.getConnection();
			String sql ="select * from user where name = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,name);		
			rs = pst.executeQuery();
		      //已被注册
			while (rs.next()){
				User u=new User();
			
				u.setId(rs.getInt("id"));
			
				 u.setMail(rs.getString("mail"));
				u.setPassword(rs.getString("password"));
			   u.setSex(rs.getString("sex"));
			   u.setType(rs.getInt("type"));
			   u.setName(rs.getString("name"));
				u.setUser(rs.getString("user"));
			  u.setAddress(rs.getString("address"));
			  u.setSignature(rs.getString("signature"));
			   u.setRegTime(rs.getDate("regTime"));
			   u.setImg(rs.getString("img"));
				return u;
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public User updateUserInfo(User user){
	
		try{
			Connection con=ConnectManager.getConnection();
			String sql ="UPDATE user  set  name= ? , sex= ?  , signature= ?  , address= ?  WHERE user like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getSex());
			pst.setString(3, user.getSignature());
			pst.setString(4, user.getAddress());
			pst.setString(5, user.getUser());
			System.out.println(pst.toString());
		   pst.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 更新头像
	 * @param user
	 * @param path
	 * @return
	 */
	public User updateUserImg(String  user,String path){
		
		try{
			Connection con=ConnectManager.getConnection();
			String sql ="UPDATE user  set  img= ?  WHERE user like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, path );
			pst.setString(2, user);
			
			
		   pst.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 更新邮箱
	 * @param user
	 * @param mail
	 * @return
	 */
	public boolean updateUserMail(String  user,String mail){
		
		try{
			Connection con=ConnectManager.getConnection();
			String sql ="UPDATE user  set  mail= ?  WHERE user like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, mail );
			pst.setString(2, user);
			
			System.out.println(pst.toString());
		 if(  pst.executeUpdate()>0){
			 return true;
		 }
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
