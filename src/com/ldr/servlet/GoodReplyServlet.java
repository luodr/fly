

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



import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class GoodReplyServlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		
		resp.setContentType("text/html; charset=UTF-8");
		 JSONObject obj = new JSONObject();
		 if(user!=null){//�Ѿ���¼
			 String articleID=req.getParameter("articleID");
			 String commentID=req.getParameter("commentID");
			
			 CommentLike like=new CommentLike();
			 like.setArticleID(Integer.parseInt( new String(articleID.getBytes(CharacterCodingUtil.getEncoding(articleID)),"UTF-8")));
			 like.setCommentID(Integer.parseInt( new String(commentID.getBytes(CharacterCodingUtil.getEncoding(commentID)),"UTF-8")));
			 like.setUser( new String(user.getUser().getBytes(CharacterCodingUtil.getEncoding(user.getUser())),"UTF-8"));
	   if( LikeDao.getInstance().insertLike(like)){
		   //��ӵ�����
		   CommentDao.getInstance().addLike(Integer.parseInt(commentID));
		   obj.put("status", 0);
		   obj.put("msg", "���޳ɹ�!");
		 
	   }else{
		 obj.put("status", 1);
	 	 obj.put("msg", "����ʧ��!���Ѿ��������");
  	}	
		 }else{ 
			 obj.put("status", 1);
			 obj.put("msg", "����ʧ��!���¼����");
		 }
		 try {
				resp.getWriter().println(obj.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
}
