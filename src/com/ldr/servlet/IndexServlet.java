

package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String hot=req.getParameter("hot");
		String page=req.getParameter("page");
		if(page==null){//Ä¬ÈÏµÚÒ»Ò³
			page="1";
		}
		int size=10;
		  req.setAttribute("page", Integer.parseInt(page));
		  int count=ArticleDao.getInstance().count();
		if(hot==null){
			 ArrayList<Article> list=ArticleDao.getInstance().findNewArticle(size, (Integer.parseInt(page)-1)*size);
			 ArrayList<HotComment> hotUser=CommentDao.getInstance().findHotUser();
			 ArrayList<Article> views=ArticleDao.getInstance().viewsArticleDESC(10,0);
			 
			 
			 
			    req.setAttribute("views", views);
			   req.setAttribute("hotUser", hotUser);
		     req.setAttribute("list", list);
		     req.setAttribute("hot", false);
		}else{
			 ArrayList<Article> hots=ArticleDao.getInstance().replyArticleDESC(size, (Integer.parseInt(page)-1)*size);
			 ArrayList<Article> views=ArticleDao.getInstance().viewsArticleDESC(10,0);
		     req.setAttribute("views", views);
		     req.setAttribute("list", hots);
			 ArrayList<HotComment> hotUser=CommentDao.getInstance().findHotUser();
			   req.setAttribute("hotUser", hotUser);
			   req.setAttribute("hot", true);
		}
	  
		
		 req.setAttribute("count", count);
    	 req.getRequestDispatcher("./static/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	

	}
}
