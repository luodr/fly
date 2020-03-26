package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class ReportServet  extends HttpServlet{
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	//  req.getRequestDispatcher("/static/reg.jsp").forward(req, resp);

}
  
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
String value=req.getParameter("value");
resp.setContentType("text/html; charset=UTF-8");
String articleId=req.getParameter("articleId");
   if(value!=null&&articleId!=null){
	 HttpSession session=req.getSession();
	User user=(User)session.getAttribute("user");
	 JSONObject obj = new JSONObject();
	if(user!=null){
		Report report=new Report();
		report.setArticleID(Integer.parseInt(articleId));
		report.setUser(user.getName());
		report.setContent(value);
	 if(  ReportDao.getInstance().insertReport(report)){
		 ArticleDao.getInstance().addReport(Integer.parseInt(articleId));
		 obj.put("code", 0);
			obj.put("msg", "举报成功!");
	 }else{
		 obj.put("code", 1);
	    obj.put("msg", "举报失败！你已经举报过该帖子!");
	 }
		
	 }else{
		   obj.put("code", 1);
		    obj.put("msg", "举报失败！请先登录!");
		 
	 }
	try {
		resp.getWriter().println(obj.toJSONString());
	} catch (IOException e) {
		e.printStackTrace();
	}
 
     }
   
	}
}
