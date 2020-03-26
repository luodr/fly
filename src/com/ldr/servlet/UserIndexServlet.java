

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

public class UserIndexServlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	         
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		 if(user!=null){//ÒÑ¾­µÇÂ¼
			 System.out.println("xxxxxx"+user.getUser());
              List myArticles=ArticleDao.getInstance().findByUser(user.getUser());
              req.setAttribute("list", myArticles);
	    	 req.getRequestDispatcher("./static/user/index.jsp").forward(req, resp);
		 }else{ //Ã»µÇÂ¼
			 resp.sendRedirect(req.getContextPath()+"/login");
		 }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	

	}
}
