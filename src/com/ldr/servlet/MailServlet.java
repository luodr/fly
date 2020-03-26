

package com.ldr.servlet;



import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class MailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  JSONObject obj = new JSONObject();
		  resp.setContentType("text/html; charset=UTF-8");
		  String mail=req.getParameter("mail");
		  User user=(User)req.getSession().getAttribute("user");
		try {
			if(user!=null&&mail!=null)
			{
				MailSend send=new MailSend();
				 String vcode="";
	             //随机生成6位验证码 
	             for (int i = 0; i < 6; i++) {
	                 vcode = vcode + (int)(Math.random() * 9);
	             }
				//发送邮箱
				   send.sendMail(mail,user.getName(),vcode);
				  req.getSession().setAttribute("mailVcode", vcode);
				  req.getSession().setAttribute("mail", mail);
				  obj.put("code",0);
	       	      obj.put("msg", "邮箱验证码已发送");
				
			}
		} catch (Exception e) {
			  obj.put("code",1);
	       	   obj.put("msg", "邮箱验证码发送失败");
		}

	      try {
				resp.getWriter().println(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		  JSONObject obj = new JSONObject();
		String mail=req.getParameter("mail");
		String yzm=req.getParameter("yzm");
		HttpSession session=req.getSession();
		resp.setContentType("text/html; charset=UTF-8");
		User user=(User)session.getAttribute("user");
		if(user!=null){
			if(session.getAttribute("mailVcode").equals(yzm.trim())&& session.getAttribute("mail").equals(mail.trim())){
				
				if(UserDao.getInstance().updateUserMail(user.getUser(), mail)){
					obj.put("code",0);
		       	    obj.put("msg", "邮箱验绑定成功");
		       	 user.setMail(mail); 
				}else{
					obj.put("code",1);
		       	      obj.put("msg", "邮箱验绑定失败请重试");
				}
				 
			}else{
				 obj.put("code",1);
		       	   obj.put("msg", "验证码错误");
			}
		
		}else{
			 obj.put("code",1);
	       	   obj.put("msg", "登录已过期");
		}
		 try {
				resp.getWriter().println(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
