

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

public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

     
     
     String itemID=req.getParameter("itemid");
     if(itemID!=null){
	Article article= ArticleDao.getInstance().findByID(Integer.parseInt(itemID));
	 ArrayList<Article> views=ArticleDao.getInstance().viewsArticleDESC(10,0);
	    req.setAttribute("views", views);
	 if(article!=null)
	 {
			HttpSession session=req.getSession();
			User user=(User)session.getAttribute("user");
			ArrayList<Comment> comments= CommentDao.getInstance().findByarticleId(article.getId());
			req.setAttribute("comments", comments);
			req.setAttribute("article", article);
			if(user!=null){
			ArrayList<Integer> likeList=LikeDao.getInstance().findByUserAndArticle(user.getUser(), article.getId());
			 req.setAttribute("likeList", likeList);
			//没通过审核 但不是该用户的帖子且不是管理员 跳到404
			 System.out.println("管理员"+user.getType());
			 if(article.getAudit()==0&&(!user.getUser().equals(article.getUserName())&&user.getType()!=1)){
				 req.getRequestDispatcher("./static/404.jsp").forward(req, resp);
				 return ;
			 }
			 
		
		
		
	
		}else{
			ArrayList<Integer> likeList=new ArrayList<Integer>();
			 req.setAttribute("likeList", likeList);
			 if(article.getAudit()==0){//如果用户没登录 且没通过审核  跳到404
				 req.getRequestDispatcher("./static/404.jsp").forward(req, resp);
				 return ;
			 }
		}
			ArticleDao.getInstance().addView(article.getId());
	    req.getRequestDispatcher("./static/jie/detail.jsp").forward(req, resp);
	    return ;
	 } 
     }
    	 req.getRequestDispatcher("./static/404.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	

	}
}
