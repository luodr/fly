

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

public class DeleteCommentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    String articleId=req.getParameter("articleId");
	    String commentID=req.getParameter("commentID");
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		  JSONObject obj = new JSONObject();
	   if(user!=null){
		   
		  
		
			   if(CommentDao.getInstance().deleteComment(Integer.parseInt(commentID), user.getUser(), Integer.parseInt(articleId))){
				  
					 ArticleDao.getInstance().reduceReply(Integer.parseInt(articleId));
				    obj.put("code", 0);
				    obj.put("msg", "É¾³ý³É¹¦");
				    
			   }else{
				   obj.put("code",1);
				    obj.put("msg", "É¾³ýÊ§°Ü");
			   }
		  
		    try {
				resp.getWriter().println(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	   
	   
	}
}
