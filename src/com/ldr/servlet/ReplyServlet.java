

package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
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



import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class ReplyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
     String itemID=req.getParameter("itemid");
     
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		 JSONObject obj = new JSONObject();
		 if(user!=null){//已经登录
			 String articleId=req.getParameter("articleId");
		     String responderName=req.getParameter("responderName");
		     String content=req.getParameter("content");
		     String zz=req.getParameter("zz");
		     Comment comment=new Comment();
		     if(articleId!=null){
                int   id=Integer.parseInt(articleId);
			     comment.setArticleid(id);
			     ArticleDao.getInstance().addReply(id);
			     comment.setCreationdate(new Timestamp(new Date().getTime()));
			     comment.setCommentcontent(content);
			    
			     content=  new String(responderName.getBytes(CharacterCodingUtil.getEncoding(responderName)),"UTF-8") ;
			  
			     if(responderName!=null&&content.indexOf("@"+responderName)!=-1){
			    
			     comment.setResponderName( new String(responderName.getBytes(CharacterCodingUtil.getEncoding(responderName)),"UTF-8"));
			     }else{
			    	   comment.setResponderName(new String(zz.getBytes(CharacterCodingUtil.getEncoding(zz)),"UTF-8"));
			     }
			     comment.setUsername(user.getUser());
			     CommentDao.getInstance().insertComment(comment);
			        obj.put("code", 0);
					obj.put("msg", "评论成功!");
		     }
		  
		     else{
		         obj.put("code", 1);
		    	 obj.put("msg", "评论失败!");
		     }
            
		 }else{ //没登录
		      obj.put("code", 1);
				obj.put("msg", "请先登录!!");
		 }
			try {
				resp.getWriter().println(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	     
	}
}
