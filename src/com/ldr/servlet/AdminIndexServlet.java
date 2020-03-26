

package com.ldr.servlet;



import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class AdminIndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
          
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		 if(user!=null){//已经登录
			
            
              if(user.getType()==1){ //是管理员
            	  req.getRequestDispatcher("./static/admin/index.jsp").forward(req, resp);
  			}else{ //不是管理员
  			
  				resp.sendRedirect(req.getContextPath()+"/index");
  			}
		 }else{ //没登录
			 resp.sendRedirect(req.getContextPath()+"/login");
		 }
		
    	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
	

	}
}
