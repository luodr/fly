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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class RegisterServet  extends HttpServlet{
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	  req.getRequestDispatcher("/static/register.jsp").forward(req, resp);

}
  
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
String name=req.getParameter("user");
String password=req.getParameter("password");
String type=req.getParameter("type");  
if(name!=null&&password!=null&type!=null){
	UserDao.getInstance().registUser(name,password,Integer.parseInt(type));
	req.getRequestDispatcher("/static/login.jsp").forward(req, resp);
}
	}
}
