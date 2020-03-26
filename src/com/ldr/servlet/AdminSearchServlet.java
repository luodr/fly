

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

public class AdminSearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
    	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		String search=req.getParameter("search");
	      ArrayList<Article> list=null;
	    		  if(search!=null)
	    		  {
	    			  try {
						list=	  ArticleDao.getInstance().searchArticle(new String(search.getBytes("ISO-8859-1"),"UTF-8"),true);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		  }else{
	    				return ;	    		  }

			    Gson gson2=new Gson();
	
			    Message m=new Message();
			    m.setCode(0);
			    m.setData(list);
			    m.setMsg("");
			    m.setCount(list.size());
			    try {
					resp.getWriter().println(gson2.toJson(m));
				} catch (IOException e) {
					e.printStackTrace();
				}
    	
	}
}
