

package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class SetInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
   
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		resp.setContentType("text/html; charset=UTF-8");
		String address=req.getParameter("address");
		String name=req.getParameter("name");
		String sign=req.getParameter("sign");
				User user=(User)req.getSession().getAttribute("user");
		if(user!=null){
			user.setAddress(address);
			user.setSignature(sign);
			
			
		     JSONObject obj = new JSONObject();
		     System.out.println(user.getName().equals(name));
		     System.out.println(name);
		     System.out.println(UserDao.getInstance().verifyName(name));
			if(!user.getName().equals(name)&&!UserDao.getInstance().verifyName(name)){
				
				obj.put("msg", "昵称已存在，请换一个!");
			}
			else{
				user.setName( name);
				UserDao.getInstance().updateUserInfo(user);
				
				obj.put("code", 0);
				obj.put("msg", "修改成功!");
			}
					try {
						resp.getWriter().println(obj.toJSONString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}else{
			
		}

	}
}
