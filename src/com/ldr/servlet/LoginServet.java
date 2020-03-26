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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class LoginServet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("/static/login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("user");
		String password = req.getParameter("password");
		User user = UserDao.getInstance().findByUser(userName);
		if(user!=null&&user.getPassword().equals(password.trim())){
			//登录成功!
			HttpSession session=req.getSession();
			session.setAttribute("user", user);
			if(user.getType()==1){ //管理员登录  先跳到后台首页
				resp.sendRedirect(req.getContextPath()+"/adminIndex");
			}else{
			
				resp.sendRedirect(req.getContextPath()+"/index");
			}
		
		}else{//登录失败!
			req.getRequestDispatcher("/static/login.jsp").forward(req, resp);
		}

	}
}
