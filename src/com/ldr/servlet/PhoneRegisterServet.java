package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneRegisterServet  extends HttpServlet{
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	  req.getRequestDispatcher("/static/reg.jsp").forward(req, resp);

}
  
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
String  userName=req.getParameter("user");
String password=req.getParameter("password");
String type=req.getParameter("rePassword");
resp.setContentType("text/html; charset=UTF-8");
String name=req.getParameter("username");
String yzm=req.getParameter("yzm");
if(userName!=null&&password!=null&type!=null){
	 JSONObject obj = new JSONObject();
	User user =new User();
	user.setPassword(password);
	user.setUser(userName);
	user.setRegTime(new Date(new java.util.Date().getTime()));
	user.setName(name);
	user.setType(0);
	HttpSession session=req.getSession();
	System.out.println(session.getAttribute("yzm")+"---"+yzm);
	if(yzm==null||!yzm.equals(session.getAttribute("yzm")))
	{
		 obj.put("code", 1);
    	 obj.put("msg", "验证码有误");
	}else
	 if(userName.getBytes().length != userName.length()){
		 obj.put("code", 1);
    	 obj.put("msg", "账号不能包含特殊字符和中文");
	 }else
	  //昵称是唯一的
    if(!UserDao.getInstance().verifyName(name)){
    	 obj.put("code", 1);
    	 obj.put("msg", "昵称已存在，请换一个!");
    }else
	if(userName.trim().equals(session.getAttribute("PhoneNumbers"))&&UserDao.getInstance().registUser(user)){
		 obj.put("code", 0);
    	 obj.put("msg", "注册成功,去登陆吧!");
	}else{
		 obj.put("code", 1);
    	 obj.put("msg", "手机号已被注册!");
	}
	try {
		resp.getWriter().println(obj.toJSONString());
	} catch (IOException e) {
		e.printStackTrace();
	}
//	req.getRequestDispatcher("/static/login.jsp").forward(req, resp);
}
	}
}
