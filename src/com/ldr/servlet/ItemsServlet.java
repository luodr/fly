

package com.ldr.servlet;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class ItemsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String type=req.getParameter("type");
		String page=req.getParameter("page");
	
		String limit=req.getParameter("limit");
		if(page==null&&limit==null){
			page="1";
			limit="10";
		}
		 int p=Integer.parseInt(page)-1;
         int l=Integer.parseInt( limit);
	      ArrayList<Article> list=null;
	      int count=0;
	    		  if(type!=null)
	    		  {
	    			  type=new String(type.getBytes("ISO-8859-1"),"UTF-8");
	    			  count=  ArticleDao.getInstance().countType(type);
	    			  list=	  ArticleDao.getInstance().searchType(type,p*l,l);
	    		  }else{
	    			  count=  ArticleDao.getInstance().count();
	    				list=ArticleDao.getInstance().findPage(p*l,l);
	    		  }
			    Gson gson2=new Gson();
			    Message m=new Message();
			    m.setCode(0);
			    m.setData(list);
			    m.setMsg("");
			    m.setCount(count);
			    try {
					resp.getWriter().println(gson2.toJson(m));
				} catch (IOException e) {
					e.printStackTrace();
				}
    	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		String type=req.getParameter("type");
		String page=req.getParameter("page");
		String limit=req.getParameter("limit");
		if(page==null&&limit==null){
			page="1";
			limit="10";
		}
		
		 int p=Integer.parseInt(page)-1;
         int l=Integer.parseInt( limit);
         int count=0;
	      ArrayList<Article> list=null;
	    		  if(type!=null)
	    		  {
	    			  try {
	    				  count=  ArticleDao.getInstance().countType(type);
						list=	  ArticleDao.getInstance().searchType(new String(type.getBytes("ISO-8859-1"),"UTF-8"),p*l,l);
					} catch (UnsupportedEncodingException e) {
						
						e.printStackTrace();
					}
	    		  }else{
	    			  count=  ArticleDao.getInstance().count();
	    				list=ArticleDao.getInstance().findPage(p*l,l);
	    		  }

			    Gson gson2=new Gson();
	
			    Message m=new Message();
			    m.setCode(0);
			    m.setData(list);
			    m.setMsg("");
			    m.setCount(count);
			    try {
					resp.getWriter().println(gson2.toJson(m));
				} catch (IOException e) {
					e.printStackTrace();
				}
    	
	}
}
