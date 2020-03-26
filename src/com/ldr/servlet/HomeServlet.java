

package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.google.gson.Gson;
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	String name=req.getParameter("name");
	HttpSession session=req.getSession();
	User user=(User)session.getAttribute("user");

	if(name!=null&&((name=new String(name.getBytes(CharacterCodingUtil.getEncoding(name)),"UTF-8"))!=null)&&(user==null||!name.equals(user.getName()))){ //查询别人的主页
		
	
		User your_user=(User)UserDao.getInstance().findByName(name);
     	if(your_user!=null){
		   ArrayList<Article>  list=ArticleDao.getInstance().findByName(name);
		   ArrayList<UserComment> userComments=CommentDao.getInstance().findUserComments(your_user.getUser(),10 );
		   System.out.println("name"+name);
           req.setAttribute("list", list);
           req.setAttribute("user", your_user);
           req.setAttribute("userComments", userComments);
		   req.getRequestDispatcher("./static/user/home.jsp").forward(req, resp);
	   }
	}else{//查询自己的主页
			if(user!=null){
	           ArrayList<Article>  list=ArticleDao.getInstance().findByUser(user.getUser());
	           req.setAttribute("list", list);
	           req.setAttribute("user", user);
	           ArrayList<UserComment> userComments=CommentDao.getInstance().findUserComments(user.getUser(),10 );
	           req.setAttribute("userComments", userComments);
	           //req.setAttribute("user", user.getUser());
				req.getRequestDispatcher("./static/user/home.jsp").forward(req, resp);
			}else{  //不是查询别人又没登陆的 -- 去登录
				 resp.sendRedirect(req.getContextPath()+"/login");
			}
		
	}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	

	}
}
