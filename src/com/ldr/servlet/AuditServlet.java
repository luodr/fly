

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
//get 用于 审核      post用于查询审核未通过的
public class AuditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String articleId=req.getParameter("articleId");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		 JSONObject obj = new JSONObject();
		if(user!=null&&user.getType()==1)
		{
		boolean b=	ArticleDao.getInstance().auditArticle(Integer.parseInt(articleId));
		if(b){
			 obj.put("code", 0);
	    	 obj.put("msg", "审核成功");
		}else{
			 obj.put("code", 1);
	    	 obj.put("msg", "审核失败,请重试");
		}
		} else{
			 obj.put("code", 1);
	    	 obj.put("msg", "审核失败,请登录");
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
		    		  if(type!=null)
		    		  {
		    			  try {
		    				  type=new String(type.getBytes("ISO-8859-1"),"UTF-8");
							list=	  ArticleDao.getInstance().FindNotAuditByType(type,p*l,l);
							Gson gson2=new Gson();
							int count=ArticleDao.getInstance().countTypeNoAudit(type);
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
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		  }

    	
	}
}
