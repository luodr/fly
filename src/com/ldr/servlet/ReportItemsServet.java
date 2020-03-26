package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;

public class ReportItemsServet  extends HttpServlet{
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	//  req.getRequestDispatcher("/static/reg.jsp").forward(req, resp);

}
  
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	  resp.setContentType("text/html; charset=UTF-8");
		String type=req.getParameter("type");
		
		
		String page=req.getParameter("page");
		String limit=req.getParameter("limit");
		if(page==null&&limit==null){
			page="1";
			limit="10";
		}
		 int p=Integer.parseInt(page)-1;
         int l=Integer.parseInt( limit);
	      ArrayList<Article> list=null;
	    		  if(type!=null)
	    		  {
	    			  try {
						list=	  ArticleDao.getInstance().reports(new String(type.getBytes("ISO-8859-1"),"UTF-8"),p*l,l);
						Gson gson2=new Gson();
						
					    Message m=new Message();
					    m.setCode(0);
					    m.setData(list);
					    m.setMsg("");
					    m.setCount(list.size());
					    try {
							resp.getWriter().println(gson2.toJson(m));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		  }

			    
   
	}
}
