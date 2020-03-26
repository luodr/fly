

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

import org.json.simple.JSONObject;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	  String delete=req.getParameter("articleId");
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		  JSONObject obj = new JSONObject();
	   if(user!=null){
		   
		   if(user.getType()==1){//如果是管理员可以直接删除
			   ArticleDao.getInstance().deleteArticle(Integer.parseInt(delete));
				  
			    obj.put("code", 0);
			    obj.put("msg", "删除成功");
		   }else{//如果不是管理员 要验证是否是作者进行删除
			Article article= ArticleDao.getInstance().findByID(Integer.parseInt(delete));
			   if(article.getUserName().equals(user.getUser())){
				   ArticleDao.getInstance().deleteArticle(Integer.parseInt(delete));
					  
				    obj.put("code", 0);
				    obj.put("msg", "删除成功");
			   }
		   }
		    try {
				resp.getWriter().println(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	   
	   
	}
}
