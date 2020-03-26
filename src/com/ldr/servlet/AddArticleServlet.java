

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

public class AddArticleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
          
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		 if(user!=null){//已经登录
			
              req.getRequestDispatcher("./static/jie/add.jsp").forward(req, resp);
		 }else{ //没登录
			 resp.sendRedirect(req.getContextPath()+"/login");
		 }
		
    	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
	//class     title  project  L_version  content
		
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		 if(user!=null){//已经登录
			String type=req.getParameter("class");
			String title=req.getParameter("title");
			String project=req.getParameter("project");
			String content=req.getParameter("content");
			String L_version=req.getParameter("L_version");
			Article article=new Article();
			article.setContent(content);
			article.setTitle(title);
			article.setType(type);
			article.setType2(project);
			article.setL_version(L_version);
			
		
			article.setUserName(user.getUser());
			article.setCreationDate(new Timestamp(new java.util.Date().getTime()));
			 article.setName(user.getName());
			int itemId= ArticleDao.getInstance().insertArticle(article);
			 JSONObject obj = new JSONObject();
			    obj.put("code",0 );
				obj.put("msg", "发布成功!");
				obj.put("itemId", itemId);
				
				try {
					resp.getWriter().println(obj.toJSONString());
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		 }else{ //没登录
			 try {
				resp.sendRedirect(req.getContextPath()+"/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }

	}
}
