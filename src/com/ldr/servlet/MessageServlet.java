

package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class MessageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
	        ArrayList<UserComment> list=CommentDao.getInstance().findMyReply(user.getName(), 50);
	            	 req.setAttribute("list", list);
		 
		 	 req.getRequestDispatcher("./static/user/message.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	String all=req.getParameter("all");
	HttpSession session=req.getSession();
	User user=(User)session.getAttribute("user");
	if(user!=null)
	{
        CommentDao.getInstance().clearMyReply(user.getUser());
        resp.getWriter().println("");
	}

	}
}
