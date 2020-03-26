

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



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  String search=req.getParameter("search");
		  String type=req.getParameter("type");
		  String hot=req.getParameter("hot");
	
		  req.setAttribute("hot",hot!=null);
		  ArrayList<Article> views=ArticleDao.getInstance().viewsArticleDESC(10,0);
		    req.setAttribute("views", views);
		  if(search!=null){
			  search=new String(search.getBytes("ISO-8859-1"),"UTF-8");
			     ArrayList<Article>  list=ArticleDao.getInstance().searchArticle(search,hot!=null);
			    req.setAttribute("list", list);
			    req.setAttribute("param", "search="+search);
			    req.getRequestDispatcher("./static/jie/index.jsp").forward(req, resp);
			   
		  }else if(type!=null){
			  type=new String(type.getBytes("ISO-8859-1"),"UTF-8");
			     ArrayList<Article>  list=ArticleDao.getInstance().searchType(type,hot!=null);
			    req.setAttribute("list", list);
			    req.setAttribute("type", type);
			    req.setAttribute("param", "type="+type);
			    req.getRequestDispatcher("./static/jie/index.jsp").forward(req, resp);
		  }
		   
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	  
	   
	   
	}
}
